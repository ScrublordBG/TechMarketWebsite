package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import com.techmark.techmarkwebsite.services.base.GenericService;
import com.techmark.techmarkwebsite.services.base.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService, GenericService<Product> {
    private GenericRepository<Product> repository;

    @Autowired
    public ProductServiceImpl(GenericRepository<Product> repository) {
        this.repository = repository;
    }
    
    @Override
    public void create(Product product) {
        List<Product> allProducts = repository.getAll();
        if (product != null) {
            for (Product prod : allProducts) {
                if (prod.getName().equals(product.getName())) {
                    System.out.printf("Product with name: \"%s\" already exits!\n", product.getName());
                    return;
                }
            }
        }
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
        if(updatedProduct.getName().equals("")){
            updatedProduct.setName(oldProduct.getName());
        }
    
        if(updatedProduct.getPrice() == 0){
            updatedProduct.setPrice(oldProduct.getPrice());
        }
    
        if(updatedProduct.getDescription().equals("")){
            updatedProduct.setDescription(oldProduct.getDescription());
        }
    
        if(updatedProduct.getImageUrl().equals("")){
            updatedProduct.setImageUrl(oldProduct.getImageUrl());
        }
    
        if(updatedProduct.getCategory().getCategoryId() == 0){
            updatedProduct.setCategory(oldProduct.getCategory());
        }
    
        repository.update(productId, updatedProduct);
    }
    
    @Override
    public void delete(int id) {
        repository.delete(id);
    }
    
    @Override
    public List<Product> getMostRecentTen() {
        int wantedCount = 10;
        List<Product> allProducts = repository.getAll();
        int productsCount = allProducts.size();
        if (productsCount < wantedCount) {
            return allProducts;
        }
        int startIndex = productsCount - wantedCount;
        int endIndex = productsCount;
        return allProducts.subList(startIndex, endIndex);
    }
}
