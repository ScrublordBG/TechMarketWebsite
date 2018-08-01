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
	@RequestMapping(
			value = "/",
			method = RequestMethod.POST
	)
	public void createUser(@RequestBody User user) {
		service.create(user);
	}
	/*works*/
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.PUT
	)
	public void updateUser(@PathVariable("id") String idString, @RequestBody User updateUser) {
		int id = Integer.parseInt(idString);
		service.update(id, updateUser);
	}
	/*doesn't work*/
	@RequestMapping(
			value = "/",
			method = RequestMethod.DELETE
	)
	public void deleteUser(int userId) {
		service.delete(userId);
	}
	
}

