package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderSqlRepository implements GenericRepository<Order> {
	private SessionFactory factory;
	
	@Autowired
	public OrderSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public Order getById(int id) {
		Order order = null;
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			order = session.get(Order.class, id);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return order;
	}
	
	@Override
	public List<Order> getAll() {
		List<Order> orders = null;
		try (Session session = factory.openSession()) {
			session.getTransaction();
			orders = session.createQuery("from Order", Order.class).list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return orders;
	}
}