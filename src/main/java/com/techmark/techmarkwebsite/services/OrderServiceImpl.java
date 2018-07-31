package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements GenericService<Order> {
	private GenericRepository<Order> repository;
	
	@Autowired
	public OrderServiceImpl(GenericRepository<Order> repository) {
		this.repository = repository;
	}
	
	@Override
	public Order getById(int id) {
		return repository.getById(id);
	}
	
	@Override
	public List<Order> getAll() {
		return repository.getAll();
	}
}
