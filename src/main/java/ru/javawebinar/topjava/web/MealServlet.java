package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MealServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
	private List<MealWithExceed> meals = MealsUtil.getMealsWithExceeded();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("meals", meals);
		
		req.getRequestDispatcher("meals.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("meals", meals);
		
		int id = Integer.parseInt(req.getParameter("id"));
		String desc = req.getParameter("desc");
		
		for (MealWithExceed meal : meals) {
			if (meal.getId() == id) {
				meal.setDescription(desc);
			}
		}
		
		req.getRequestDispatcher("meals.jsp").forward(req, resp);
	}
}
