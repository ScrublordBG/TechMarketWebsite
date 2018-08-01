package com.techmark.techmarkwebsite.web;

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
	public void createUser(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
				User newUser = new User(firstName, lastName, username, password);
				service.create(newUser);
	}
	
	/*works (alternative appoach, but can't create user via url)*/
	/*public void createUser(@RequestBody User user) {
		service.create(user);
	}
	*/
	
	/*works (logic moved to the service layer)*/
	@PutMapping("/updateUser/{id}")
	public void updateUser(
		@PathVariable("id") String userIdString,
		@RequestParam(value = "firstName", required = false) String firstName,
		@RequestParam(value = "lastName", required = false) String lastName,
		@RequestParam(value = "username", required = false) String username,
		@RequestParam(value = "password", required = false) String password){
			int userId = Integer.parseInt(userIdString);
			User updatedUser = new User(userId, firstName, lastName, username, password);
			service.update(userId, updatedUser);
	}
	
	/*works (alternative approach, but can't update user via url)*/
	/*@PutMapping("/updateUser/{id}")
	public void updateUser(@PathVariable("id") String idString, @RequestBody User updateUser) {
		int id = Integer.parseInt(idString);
		service.update(id, updateUser);
	}
	*/
	/*works*/
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") String userIdString) {
		int userId = Integer.parseInt(userIdString);
		service.delete(userId);
	}
}

