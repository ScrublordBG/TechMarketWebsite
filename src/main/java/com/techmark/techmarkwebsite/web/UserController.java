package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.User;
import com.techmark.techmarkwebsite.services.UserServiceImpl;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	private GenericService<User> service;
	
	@Autowired
	public UserController(GenericService<User> service) { this.service = service; }
	
	@GetMapping("/{id}")
	public User getById(@PathVariable("id") String id) {
		int newId = Integer.parseInt(id);
		return service.getById(newId);
	}
	
	@GetMapping("/")
	public List<User> getAll() {
		return service.getAll();
	}
	
	
}

