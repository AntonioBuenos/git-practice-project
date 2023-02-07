package by.smirnov.gitpracticeproject.service;

import by.smirnov.gitpracticeproject.entity.Ad;

import java.util.List;

public interface AdService {

    List<Ad> findAll();

    Ad findById(long id);
}
