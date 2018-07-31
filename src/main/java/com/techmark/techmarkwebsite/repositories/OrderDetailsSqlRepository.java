package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailsSqlRepository implements GenericRepository {
	private SessionFactory factory;
	
	@Autowired
	public OrderDetailsSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Object obj) {
	
	}
	
	@Override
	public Object getById(int id) {
		return null;
	}
	
	@Override
	public List getAll() {
		return null;
	}
	
	@Override
	public void update(Object obj) {
	
	}
	
	@Override
	public void delete(int id) {
	
	}
	
}
