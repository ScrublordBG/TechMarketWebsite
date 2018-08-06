package com.techmark.techmarkwebsite.services.base;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;

import java.util.List;

public interface CategoryService extends GenericService<Category>{
	
	List<Product> getAllProductsById(int id);
	
}
