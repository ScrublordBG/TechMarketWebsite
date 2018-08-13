package com.techmark.techmarkwebsite.web.rest_controllers;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.services.base.GenericService;
import com.techmark.techmarkwebsite.services.base.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("/")
    public List<Product> getAll(){
        return service.getAll();
    }
    
    /*works*/
    @PostMapping("/addProduct")
    public void createProduct(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "price", required = false) String priceString,
        @RequestParam(value = "description", required = false) String description,
        @RequestParam(value = "imageUrl", required = false) String imageUrl,
        @RequestParam(value = "categoryId", required = false) String categoryIdString,
        @RequestParam(value = "categoryName", required = false) String categoryName) {
        int price = Integer.parseInt(priceString);
        int categoryId = Integer.parseInt(categoryIdString);
        Category category = new Category(categoryId, categoryName);
        Product newProduct = new Product(name, price, description, imageUrl, category);
        service.create(newProduct);
    }
    
    /*works*/
    @PutMapping("/updateProduct/{id}")
    public void updateProduct(
      @PathVariable("id") String productIdString,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "price", required = false) String priceString,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "imageUrl", required = false) String imageUrl,
      @RequestParam(value = "categoryId", required = false) String categoryIdString,
      @RequestParam(value = "categoryName", required = false) String categoryName
    ){
        int productId = Integer.parseInt(productIdString);
        int price = 0;
        if (!priceString.equals("")){
            price =Integer.parseInt(priceString);
        }
        
        int categoryId = 0;
        if (!categoryIdString.equals("")){
            categoryId = Integer.parseInt(categoryIdString);
        }
        
        Category category = new Category(categoryId, categoryName);
        Product updatedProduct = new Product(productId, name, price, description, imageUrl, category);
        service.update(productId, updatedProduct);
    }
    
    /*works*/
    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable("id") String productIdString) {
        int productId = Integer.parseInt(productIdString);
        service.delete(productId);
    }
}
