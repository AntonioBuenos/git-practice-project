package by.smirnov.gitpracticeproject.constants;

public interface BotConstants {
    String COMMAND_SEND = "/send";
    String COMMAND_START = "/start";
    String COMMAND_HELP = "/help";
    String COMMAND_REGISTER = "/register";
    String HELP_TEXT = """
            This bot is created to demonstrate Spring capabilities.
            You can execute commands from the main menu on the left or by typing a command:
            Type /start to see a welcome message
            Type /mydata to see data stored about yourself
            Type /help to see this message again""";
    String DEFAULT_TEXT = "Все говорят \"%s\", а ты возьми и приди! еще и подарок принеси!))";
    String START_TEXT = """
            Привет, %s, я бот! Меня создал хозяин, потому что ему лень самому общаться в телеграме.
            А теперь к делу: приходи к нему на ДР! Что скажешь? %s""";
    String SMILE_BLUSH = ":blush:";
    String YES_BUTTON = "YES_BUTTON";
    String NO_BUTTON = "NO_BUTTON";
    String YES_BUTTON_MSG = "YES_BUTTON";
    String NO_BUTTON_MSG = "NO_BUTTON";
    String ERROR = "Error occurred: {}";
    String ERROR_COMMAND_LIST = "Error setting bot's command list: {}";
    String LOG_SAVED = "user saved: {}";
    String LOG_REPLIED = "Replied to user {}";
    String ASK_REGISTRATION = "Do you really want to register?";
    String YES_BUTTON_NAME = "Yes";
    String NO_BUTTON_NAME = "No";
}
