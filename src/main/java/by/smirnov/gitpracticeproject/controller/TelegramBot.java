package by.smirnov.gitpracticeproject.controller;

import by.smirnov.gitpracticeproject.component.InlineButtons;
import by.smirnov.gitpracticeproject.config.BotConfig;
import by.smirnov.gitpracticeproject.entity.User;
import by.smirnov.gitpracticeproject.service.UserService;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static by.smirnov.gitpracticeproject.constants.BotConstants.ASK_REGISTRATION;
import static by.smirnov.gitpracticeproject.constants.BotConstants.COMMAND_HELP;
import static by.smirnov.gitpracticeproject.constants.BotConstants.COMMAND_REGISTER;
import static by.smirnov.gitpracticeproject.constants.BotConstants.COMMAND_SEND;
import static by.smirnov.gitpracticeproject.constants.BotConstants.COMMAND_START;
import static by.smirnov.gitpracticeproject.constants.BotConstants.DEFAULT_TEXT;
import static by.smirnov.gitpracticeproject.constants.BotConstants.HELP_TEXT;
import static by.smirnov.gitpracticeproject.constants.BotConstants.LOG_REPLIED;
import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON;
import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON_MSG;
import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON_NAME;
import static by.smirnov.gitpracticeproject.constants.BotConstants.SMILE_BLUSH;
import static by.smirnov.gitpracticeproject.constants.BotConstants.START_TEXT;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON_MSG;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON_NAME;
import static by.smirnov.gitpracticeproject.constants.LogConstants.LOG_MESSAGE_SENT;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final UserService userService;
    private final BotExecutor botExecutor;
    private final InlineButtons inlineButtons;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            handleMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallBackQuery(update);
        }
    }

    private void handleMessage(Update update){
        Message message = update.getMessage();
        String messageText = message.getText();
        log.info(LOG_MESSAGE_SENT, message.getChat().getUserName(), messageText);
        long chatId = message.getChatId();

        if (messageText.contains(COMMAND_SEND) && botConfig.getOwnerId() == chatId) {
            String textToSend = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
            for (User user : userService.findAll()) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(user.getChatId());
                sendMessage.setText(textToSend);
                sendMessage.enableHtml(true);
                botExecutor.executeMessage(sendMessage);
            }
        } else {
            switch (messageText) {
                case COMMAND_START -> startCommandReceived(message);
                case COMMAND_HELP -> botExecutor.executeMessage(message, HELP_TEXT);
                case COMMAND_REGISTER -> register(chatId);
                default -> botExecutor.executeMessage(message, String.format(DEFAULT_TEXT, messageText));
            }
        }
    }

    private void handleCallBackQuery(Update update){
        String callbackData = update.getCallbackQuery().getData();
        Message message = update.getCallbackQuery().getMessage();
        int messageId = message.getMessageId();
        long chatId = message.getChatId();

        if (callbackData.equals(YES_BUTTON)) {
            botExecutor.editMessage(YES_BUTTON_MSG, chatId, messageId);
        } else if (callbackData.equals(NO_BUTTON)) {
            botExecutor.editMessage(NO_BUTTON_MSG, chatId, messageId);
        }
    }

    private void register(long chatId) {
        SendMessage message = inlineButtons.getButtons(chatId);
        botExecutor.executeMessage(message);
    }

    private void startCommandReceived(Message message) {
        userService.registerUser(message);
        String name = message.getChat().getFirstName();
        String answer = EmojiParser.parseToUnicode(String.format(START_TEXT, name, SMILE_BLUSH));

        log.info(LOG_REPLIED, message.getChat().getUserName());
        botExecutor.executeMessage(message, answer);
    }

}
