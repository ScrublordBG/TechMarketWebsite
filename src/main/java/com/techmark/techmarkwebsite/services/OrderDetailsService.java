package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.OrderDetails;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService implements GenericService<OrderDetails> {
	private GenericRepository<OrderDetails> repository;
	
	@Autowired
	public OrderDetailsService(GenericRepository<OrderDetails> repository) {
		this.repository = repository;
	}
	
	@Override
	public OrderDetails getById(int id) {
		return repository.getById(id);
	}
	
	@Override
	public List<OrderDetails> getAll() {
		return repository.getAll();
	}
}
