package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserSqlRepository implements UserRepository {
	private SessionFactory factory;
	
	@Autowired
	public UserSqlRepository(SessionFactory factory) {this.factory = factory;}
	
	@Override
	public User getById(int userId) {
		User user = null;
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			user = session.get(User.class, userId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return user;
	}
	
	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			users = session.createQuery("from User").list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return users;
	}
}