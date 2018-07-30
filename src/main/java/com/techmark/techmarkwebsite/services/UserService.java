package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.User;

import java.util.List;

public interface UserService {
	
	User getById(int id);
	List<User> getAll();
}
