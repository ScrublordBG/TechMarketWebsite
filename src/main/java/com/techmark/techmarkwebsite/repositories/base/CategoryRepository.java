package com.techmark.techmarkwebsite.repositories.base;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;

import java.util.List;

public interface CategoryRepository {
    
    List<Product> getAllByCategoryId(int id);
    
    void create(Category category);
    
    Category getById(int id);
    
    List<Category> getAll();
    
    void update(int id, Category updatedCategory);
    
    void delete(int id);

}
