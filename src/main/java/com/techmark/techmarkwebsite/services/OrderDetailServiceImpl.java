package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailId;
import com.techmark.techmarkwebsite.models.OrderDetail;
import com.techmark.techmarkwebsite.repositories.base.OrderDetailRepository;
import com.techmark.techmarkwebsite.services.base.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	private OrderDetailRepository repository;
	
	@Autowired
	public OrderDetailServiceImpl(OrderDetailRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void create(OrderDetail orderDetail) {
		repository.create(orderDetail);
	}
	
	@Override
	public OrderDetail getById(OrderDetailId orderDetailId) {
		return repository.getById(orderDetailId);
	}
	
	@Override
	public List<OrderDetail> getAll() {
		return repository.getAll();
	}
	
	@Override
	public void update(OrderDetailId orderDetailId, OrderDetail updateOrderDetail) {
		OrderDetail oldOrderDetail = repository.getById(orderDetailId);
		if(updateOrderDetail.getProductPrice() <= 0){
			updateOrderDetail.setProductPrice(oldOrderDetail.getProductPrice());
		}
		
		if(updateOrderDetail.getQuantity() < 0){
			updateOrderDetail.setQuantity(oldOrderDetail.getQuantity());
		}
		repository.update(orderDetailId, updateOrderDetail);
	}
	
	@Override
	public void delete(OrderDetailId orderDetailId) {
		repository.delete(orderDetailId);
	}
	
	@Override
	public List<OrderDetail> getAllByOrderId(int orderId) {
		return repository.getAllByOrderId(orderId);
	}
}
