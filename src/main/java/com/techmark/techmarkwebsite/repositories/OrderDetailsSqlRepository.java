package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.OrderDetails;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailsSqlRepository implements GenericRepository<OrderDetails> {
	private SessionFactory factory;
	
	@Autowired
	public OrderDetailsSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(OrderDetails obj) {
	
	}
	
	@Override
	public OrderDetails getById(int id) {
		return null;
	}
	
	@Override
	public List<OrderDetails> getAll() {
		return null;
	}
	
	@Override
	public void update(int id, OrderDetails obj) {
	
	}
	
	@Override
	public void delete(int id) {
	
	}
}
