package by.smirnov.gitpracticeproject.component;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static by.smirnov.gitpracticeproject.constants.BotConstants.ASK_REGISTRATION;
import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON;
import static by.smirnov.gitpracticeproject.constants.BotConstants.NO_BUTTON_NAME;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON;
import static by.smirnov.gitpracticeproject.constants.BotConstants.YES_BUTTON_NAME;

public class InlineButtonsImpl implements InlineButtons{

    public SendMessage getButtons(Long chatId){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(ASK_REGISTRATION);

        InlineKeyboardMarkup keybdMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keybd = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();

        var yesButton = new InlineKeyboardButton();
        yesButton.setText(YES_BUTTON_NAME);
        yesButton.setCallbackData(YES_BUTTON);

        var noButton = new InlineKeyboardButton();
        noButton.setText(NO_BUTTON_NAME);
        noButton.setCallbackData(NO_BUTTON);

        buttonsRow.add(yesButton);
        buttonsRow.add(noButton);

        keybd.add(buttonsRow);

        keybdMarkup.setKeyboard(keybd);
        message.setReplyMarkup(keybdMarkup);

        return message;
    }
}
