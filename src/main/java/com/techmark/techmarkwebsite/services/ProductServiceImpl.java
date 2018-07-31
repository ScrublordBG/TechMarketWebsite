package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.ProductSqlRepository;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements GenericService<Product> {
    private GenericRepository<Product> repository;

    @Autowired
    public ProductServiceImpl(GenericRepository<Product> repository) {
        this.repository = repository;
    }

    @Override
    public Product getById(int id) {
        return repository.getById(id);
    }
    
    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }
}
