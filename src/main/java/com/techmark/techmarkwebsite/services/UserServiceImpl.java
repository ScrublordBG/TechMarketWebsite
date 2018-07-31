package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.User;
import com.techmark.techmarkwebsite.repositories.UserSqlRepository;
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
	public void update(User updateUser) {
		repository.update(updateUser);
	}
	
	@Override
	public void delete(int id) {
		repository.delete(id);
	}
}
