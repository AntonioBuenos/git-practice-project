package by.smirnov.gitpracticeproject.service;

import by.smirnov.gitpracticeproject.entity.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface UserService {
    void registerUser(Message message);
    List<User> findAll();
    User findById(long id);
}
