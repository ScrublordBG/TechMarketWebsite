package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.OrderDetails;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements GenericService<OrderDetails> {
	private GenericRepository<OrderDetails> repository;
	
	@Autowired
	public OrderDetailsServiceImpl(GenericRepository<OrderDetails> repository) {
		this.repository = repository;
	}
	
	@Override
	public void create(OrderDetails orderDetails) {
		repository.create(orderDetails);
	}
	
	@Override
	public OrderDetails getById(int id) {
		return repository.getById(id);
	}
	
	@Override
	public List<OrderDetails> getAll() {
		return repository.getAll();
	}
	
	@Override
	public void update(int id, OrderDetails updateOrderDetails) {
		repository.update(id, updateOrderDetails);
	}
	
	@Override
	public void delete(int id) {
		repository.delete(id);
	}
}
