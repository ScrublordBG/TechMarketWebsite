package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.OrderDetails;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order_details")
public class OrderDetailsController {
	private GenericService<OrderDetails> service;
	
	@Autowired
	public OrderDetailsController(GenericService<OrderDetails> service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public List<OrderDetails> getAll() {
		return service.getAll();
	}
}
