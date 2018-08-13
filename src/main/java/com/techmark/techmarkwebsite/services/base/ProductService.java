package com.techmark.techmarkwebsite.services.base;

import com.techmark.techmarkwebsite.models.Product;

import java.util.List;

public interface ProductService extends GenericService<Product> {
	
	List<Product> getMostRecentTen();
}
