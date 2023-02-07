package by.smirnov.gitpracticeproject.repository;

import by.smirnov.gitpracticeproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>,
        JpaRepository<User, Long> {
}
