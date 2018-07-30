package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.User;

import java.util.List;

public interface UserRepository {
	User getById(int id);
	
	List<User> getAll();
}
