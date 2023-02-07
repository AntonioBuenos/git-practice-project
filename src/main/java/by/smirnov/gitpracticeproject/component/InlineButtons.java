package by.smirnov.gitpracticeproject.component;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface InlineButtons {

    SendMessage getButtons(Long chatId);
}
