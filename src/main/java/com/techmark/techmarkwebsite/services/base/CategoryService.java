package com.techmark.techmarkwebsite.services.base;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;

import java.util.List;

public interface CategoryService {
	
	List<Product> getAllProductsById(int id);
	
	void create(Category category);
	
	Category getById(int id);
	
	List<Category> getAll();
	
	void update(int id, Category updatedCategory);
	
	void delete(int id);
}
