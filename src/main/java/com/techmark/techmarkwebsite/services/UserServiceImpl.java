package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.User;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements GenericService<User> {
	private GenericRepository<User> repository;
	
	@Autowired
	public UserServiceImpl(GenericRepository<User> repository) { this.repository = repository;	}
	
	@Override
	public void create(User user) {
		List<User> allUsers = repository.getAll();
		if (user != null) {
			for (User u : allUsers) {
				if (u.getUsername().equals(user.getUsername())) {
					System.out.printf("User with username: \"%s\" already exits!\n", user.getUsername());
					return;
				}
			}
		}
		repository.create(user);
	}
	
	@Override
	public User getById(int id) {
		return repository.getById(id);
	}
	
	@Override
	public List getAll() {
		return repository.getAll();
	}
	
	@Override
	public void update(int oldUserId, User updatedUser) {
		User oldUser = repository.getById(oldUserId);
		if(updatedUser.getFirstName().equals("")){
			updatedUser.setFirstName(oldUser.getFirstName());
		}
		
		if(updatedUser.getLastName().equals("")){
			updatedUser.setLastName(oldUser.getLastName());
		}
		
		if(updatedUser.getUsername().equals("")){
			updatedUser.setUsername(oldUser.getUsername());
		}
		
		if(updatedUser.getPassword().equals("")){
			updatedUser.setPassword(oldUser.getPassword());
		}
		
		repository.update(oldUserId, updatedUser);
	}
	
	@Override
	public void delete(int id) {
		repository.delete(id);
	}
}
