package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.models.User;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	private GenericService<User> service;
	
	@Autowired
	public UserController(GenericService<User> service) { this.service = service; }
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") String id) {
		int newId = Integer.parseInt(id);
		return service.getById(newId);
	}
	
	@GetMapping("/")
	public List<User> getAllUsers() {
		return service.getAll();
	}
	
	/*works!*/
	@PostMapping("/addUser")
	public void createUser(@RequestBody User user) {
		service.create(user);
	}
	/*works*/
	@PutMapping("/updateUser/{id}")
	public void updateUser(@PathVariable("id") String idString, @RequestBody User updateUser) {
		int id = Integer.parseInt(idString);
		service.update(id, updateUser);
	}
	/*works*/
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") String userIdString) {
		int userId = Integer.parseInt(userIdString);
		service.delete(userId);
	}
}

