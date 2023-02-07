package by.smirnov.gitpracticeproject.repository;

import by.smirnov.gitpracticeproject.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
