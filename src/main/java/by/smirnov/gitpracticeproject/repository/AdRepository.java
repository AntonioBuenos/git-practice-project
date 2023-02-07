package by.smirnov.gitpracticeproject.repository;

import by.smirnov.gitpracticeproject.entity.Ad;
import org.springframework.data.repository.CrudRepository;

public interface AdRepository extends CrudRepository<Ad, Long> {
}
