package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderSqlRepository implements GenericRepository<Order> {
	private SessionFactory factory;
	
	@Autowired
	public OrderSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Order order) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(order);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public Order getById(int orderId) {
		Order order = null;
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			order = session.get(Order.class, orderId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return order;
	}
	
	@Override
	public List<Order> getAll() {
		List<Order> orders = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			orders = session.createQuery("from Order").list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return orders;
	}
	
	@Override
	public void update(int orderId, Order updateOrder) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Order order = session.get(Order.class, orderId);
			order.setUser(updateOrder.getUser());
			order.setDate(updateOrder.getDate());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void delete(int orderId) {
		Order order = getById(orderId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(order);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
