package com.techmark.techmarkwebsite.repositories.base;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;

import java.util.List;

public interface CategoryRepository extends GenericRepository<Category> {
    
    List<Product> getAllByCategoryId(int id);

}
