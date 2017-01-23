package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {
	
	@Autowired
	private CrudMealRepository crudRepository;
	
	@Override
	public Meal save(Meal Meal, int userId) {
		return crudRepository.save(Meal, userId);
	}
	
	@Override
	public boolean delete(int id, int userId) {
		return crudRepository.delete(id, userId);
	}
	
	@Override
	public Meal get(int id, int userId) {
		return crudRepository.get(id, userId);
	}
	
	@Override
	public List<Meal> getAll(int userId) {
		return getAll(userId);
	}
	
	@Override
	public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
		return crudRepository.getBetween(startDate, endDate, userId);
	}
}
