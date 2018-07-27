package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.ProductSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements GenericService {
    private ProductSqlRepository repository;

    @Autowired
    public ProductService(ProductSqlRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getById(int id) {
        return repository.getById(id);
    }
}
