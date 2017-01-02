package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.activation.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {
	private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private SimpleJdbcInsert insertMeal;
	
//	@Autowired
//	public JdbcMealRepositoryImpl(DataSource dataSource) {
//		insertMeal = new SimpleJdbcInsert(dataSource)
//				.withTableName("USERS")
//				.usingGeneratedKeyColumns("id");
//	}
	
	@Override
	public Meal save(Meal meal, int userId) {
		MapSqlParameterSource map = new MapSqlParameterSource()
				.addValue("meal_id", userId)
				.addValue("description", meal.getDescription())
				.addValue("calories", meal.getCalories())
				.addValue("date_time", meal.getDateTime());
		
		if (meal.isNew()) {
			insertMeal.executeAndReturnKey(map);
		} else {
			namedParameterJdbcTemplate.update(
					"UPDATE meals SET description=:description, calories=:calories, date_time=:date_time WHERE meal_id=:meal_id", map);
		}
		
		return meal;
	}
	
	@Override
	public boolean delete(int id, int userId) {
		return jdbcTemplate.update("DELETE FROM meals WHERE meal_id=?", userId) != 0;
	}
	
	@Override
	public Meal get(int id, int userId) {
		List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE meal_id=?", ROW_MAPPER, userId);
		
		return DataAccessUtils.singleResult(meals);
	}
	
	@Override
	public List<Meal> getAll(int userId) {
		return jdbcTemplate.query("SELECT * FROM meals ORDER BY date_time", ROW_MAPPER);
	}
	
	@Override
	public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
		return jdbcTemplate.query("SELECT * FROM meals", ROW_MAPPER);
	}
}
