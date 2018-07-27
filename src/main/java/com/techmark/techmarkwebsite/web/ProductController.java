package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") String id){
        return service.getById(Integer.parseInt(id));
    }
}
