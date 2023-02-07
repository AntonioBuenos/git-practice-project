package by.smirnov.gitpracticeproject.service;

import by.smirnov.gitpracticeproject.entity.Ad;
import by.smirnov.gitpracticeproject.repository.AdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService{

    private final AdRepository repository;

    @Override
    public List<Ad> findAll() {
        return repository.findAll();
    }

    @Override
    public Ad findById(long id) {
        return repository.findById(id).orElse(null);
    }
}
