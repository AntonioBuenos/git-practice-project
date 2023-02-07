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

import static by.smirnov.gitpracticeproject.constants.BotConstants.COMMAND_HELP;
import static by.smirnov.gitpracticeproject.constants.BotConstants.COMMAND_REGISTER;
import static by.smirnov.gitpracticeproject.constants.BotConstants.COMMAND_SEND;
import static by.smirnov.gitpracticeproject.constants.BotConstants.COMMAND_START;
import static by.smirnov.gitpracticeproject.constants.BotConstants.DEFAULT_TEXT;
import static by.smirnov.gitpracticeproject.constants.BotConstants.HELP_TEXT;
import static by.smirnov.gitpracticeproject.constants.BotConstants.LOG_REPLIED;
import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON;
import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON_MSG;
import static by.smirnov.gitpracticeproject.constants.BotConstants.SMILE_BLUSH;
import static by.smirnov.gitpracticeproject.constants.BotConstants.START_TEXT;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON_MSG;
import static by.smirnov.gitpracticeproject.constants.LogConstants.LOG_MESSAGE_SENT;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final UserService userService;
    private final BotExecutor botExecutor;
    private final InlineButtons inlineButtons;
    private final CallBackHandler callBackHandler;
    private final MessageHandler messageHandler;

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
            messageHandler.handleMessage(update);
        } else if (update.hasCallbackQuery()) {
            callBackHandler.handleCallBackQuery(update);
        }
    }

}
