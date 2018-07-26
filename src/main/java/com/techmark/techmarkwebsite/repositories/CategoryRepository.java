package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.Product;

import java.util.List;

public interface CategoryRepository {
    List<Product> getAllByCategoryId(int id);

}
