package com.techmark.techmarkwebsite.web.rest_controllers;

import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.models.User;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	@PostMapping("/addOrder")
	public void createOrder(
			@RequestParam("userId") String userIdString,
			@RequestParam("date") String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date newDate = format.parse(date);
		
		int userId = Integer.parseInt(userIdString);
		User user = new User(userId);
		
		Order newOrder = new Order(user, newDate);
		service.create(newOrder);
	}
	
	/*works*/
	
	/*works (logic moved to the service layer)*/
	@PutMapping("/updateOrder/{id}")
	public void updateOrder(
			@PathVariable("id") String orderIdString,
			@RequestParam(value = "userId", required = false) String userIdString,
			@RequestParam(value = "date", required = false) String date) throws ParseException {
		int orderId = Integer.parseInt(orderIdString);
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date newDate = format.parse(date);
		
		int userId = Integer.parseInt(userIdString);
		User user = new User(userId);
		
		Order updatedOrder = new Order(orderId, user, newDate);
		service.update(orderId, updatedOrder);
	}
	
	/*works*/
	@DeleteMapping("/deleteOrder/{id}")
	public void deleteOrder(@PathVariable("id") String orderIdString) {
		int orderId = Integer.parseInt(orderIdString);
		service.delete(orderId);
	}
	
}
