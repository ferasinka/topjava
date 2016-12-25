package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.MealRepository;

/**
 * GKislin
 * 06.03.2015.
 */
public class MealServiceImpl implements MealService {
	@Autowired
    private MealRepository repository;

}
