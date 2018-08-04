package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.base.CategoryRepository;
import com.techmark.techmarkwebsite.services.base.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository repository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Product> getAllProductsById(int id) {
		return repository.getAllByCategoryId(id);
	}
	
	@Override
	public void create(Category category) {
		repository.create(category);
	}
	
	@Override
	public Category getById(int id) {
		return repository.getById(id);
	}
	
	@Override
	public List<Category> getAll() {
		return repository.getAll();
	}
	
	@Override
	public void update(int id, Category updatedCategory) {
		Category oldCategory = getById(id);
		if (updatedCategory.getName() == null) {
			updatedCategory.setName(oldCategory.getName());
		}
		repository.update(id, updatedCategory);
	}
	
	@Override
	public void delete(int id) {
		repository.delete(id);
	}
}
