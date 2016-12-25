package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.web.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
		if (repository.get(id) != null && AuthorizedUser.id() == repository.get(id).getUserId()) {
			repository.remove(id);
			
			return true;
		} else {
			throw new NotFoundException("Meal not found!");
		}
    }

    @Override
    public Meal get(int id) {
    	if (repository.get(id) != null && AuthorizedUser.id() == repository.get(id).getUserId()) {
    		return repository.get(id);
		} else {
    		throw new NotFoundException("Meal not found!");
		}
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }
}

