package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private GenericService<Product> service;

    @Autowired
    public ProductController(GenericService<Product> service) {
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
    
    /*Doesn't work - CategoryID is the problem*/
    @RequestMapping(
        value = "/",
        method = RequestMethod.POST
    )
    public void createProduct(@RequestBody Product product) {
        service.create(product);
    }
    
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
        int price = Integer.parseInt(priceString);
        int categoryId = Integer.parseInt(categoryIdString);
        Category category = new Category(categoryId, categoryName);
        Product updatedProduct = new Product(productId, name, price, description, imageUrl, category);
        service.update(productId, updatedProduct);
    }
    
    /*works*/
    @DeleteMapping("/deleteProduct/{id}")
    public void deleteUser(@PathVariable("id") String productIdString) {
        int productId = Integer.parseInt(productIdString);
        service.delete(productId);
    }
    
}
