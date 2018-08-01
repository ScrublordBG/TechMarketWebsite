package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.services.ProductServiceImpl;
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
    
    /*doesn't works due to Category interrelation*/
    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.PUT
    )
    public void updateProduct(@PathVariable("id") String productIdString, @RequestBody Product updateProduct) {
        int productId = Integer.parseInt(productIdString);
        service.update(productId, updateProduct);
    }
    
    /*doesn't work*/
    @RequestMapping(
        value = "/",
        method = RequestMethod.DELETE
    )
    public void deleteProduct(int productId) {
        service.delete(productId);
    }
}
