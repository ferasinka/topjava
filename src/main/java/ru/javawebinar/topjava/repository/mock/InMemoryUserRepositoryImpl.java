package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
	private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
	private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        
        repository.remove(id);
        
        return true;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        
        repository.putIfAbsent(counter.getAndIncrement(), user);
        
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public Map<Integer, User> getAll() {
        LOG.info("getAll");
        
        return repository;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
	
		final User[] result = new User[1];
        
		repository.forEach((key, value) -> {
			if (value.getEmail().equals(email)) {
				result[0] = value;
			}
		});
		
		return result[0];
    }
}
