package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private GenericService<Order> service;
	
	@Autowired
	public OrderController(GenericService<Order> service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public Order getById(@PathVariable("id") String id) {
		int newId = Integer.parseInt(id);
		return service.getById(newId);
	}
	
	@GetMapping("/")
	public List<Order> getAll() {
		return service.getAll();
	}
	
	/*works!*/
	@RequestMapping(
			value = "/",
			method = RequestMethod.POST
	)
	public void createOrder(@RequestBody Order order) {
		service.create(order);
	}
	/*works*/
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.PUT
	)
	public void updateOrder(@PathVariable("id") String orderIdString, @RequestBody Order updateOrder) {
		int orderId = Integer.parseInt(orderIdString);
		service.update(orderId, updateOrder);
	}
	/*doesn't work*/
	@RequestMapping(
			value = "/",
			method = RequestMethod.DELETE
	)
	public void deleteOrder(int orderId) {
		service.delete(orderId);
	}
	
}
