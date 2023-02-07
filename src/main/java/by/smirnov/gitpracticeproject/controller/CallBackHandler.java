package by.smirnov.gitpracticeproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON;
import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON_MSG;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON_MSG;

@Component
@RequiredArgsConstructor
public class CallBackHandler {

    private final BotExecutor botExecutor;

    public void handleCallBackQuery(Update update){
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
}
