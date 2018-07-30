package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.User;
import com.techmark.techmarkwebsite.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository repository;
	
	@Autowired
	public UserServiceImpl(UserRepository repository) { this.repository = repository;	}
	
	@Override
	public User getById(int id) {
		return repository.getById(id);
	}
	
	@Override
	public List getAll() {
		return repository.getAll();
	}
}
