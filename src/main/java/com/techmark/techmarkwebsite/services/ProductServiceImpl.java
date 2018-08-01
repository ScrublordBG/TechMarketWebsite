package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Product;
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
    public void create(Product product) {
        repository.create(product);
    }
    
    @Override
    public Product getById(int id) {
        return repository.getById(id);
    }
    
    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }
    
    @Override
    public void update(int productId, Product updatedProduct) {
        Product oldProduct = repository.getById(productId);
        if(updatedProduct.getName() == null){
            updatedProduct.setName(oldProduct.getName());
        }
    
        if(updatedProduct.getPrice() == 0){
            updatedProduct.setPrice(oldProduct.getPrice());
        }
    
        if(updatedProduct.getDescription() == null){
            updatedProduct.setDescription(oldProduct.getDescription());
        }
    
        if(updatedProduct.getImageURL() == null){
            updatedProduct.setImageURL(oldProduct.getImageURL());
        }
    
        if(updatedProduct.getCategory().getCategoryID() == 0){
            updatedProduct.setCategory(oldProduct.getCategory());
        }
    
        repository.update(productId, updatedProduct);
    }
    
    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
