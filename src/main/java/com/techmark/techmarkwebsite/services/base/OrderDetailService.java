package com.techmark.techmarkwebsite.services.base;

import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailId;
import com.techmark.techmarkwebsite.models.OrderDetail;

import java.util.List;

public interface OrderDetailService {
	
	void create(OrderDetail orderDetail);
	
	OrderDetail getById(OrderDetailId OrderDetailId);
	
	List<OrderDetail> getAll();
	
	void update(OrderDetailId OrderDetailId, OrderDetail orderDetail);
	
	void delete(OrderDetailId OrderDetailId);
	
	List<OrderDetail> getAllByOrderId(int orderId);
}
