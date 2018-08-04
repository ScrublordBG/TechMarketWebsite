package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.services.base.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	private CategoryService service;
	
	@Autowired
	public CategoryController(CategoryService service) {
		this.service = service;
	}
	
	/*works*/
	@GetMapping("/getAllProductsByCategoryId/{id}")
	public List<Product> getAllProductsByCategoryId(@PathVariable("id") String idString) {
		int id = Integer.parseInt(idString);
		return service.getAllProductsById(id);
	}
	@GetMapping("/{id}")
	public Category getById(@PathVariable("id") String id){
		return service.getById(Integer.parseInt(id));
	}
	
	@GetMapping("/")
	public List<Category> getAllCategories() { return service.getAll();	}
	
	/*works*/
	@PostMapping("/addCategory")
	public void createCategory(
			@RequestParam(value = "name", required = false) String name) {
		Category newCategory = new Category(name);
		service.create(newCategory);
	}
	
	/*works*/
	@PutMapping("/updateCategory/{id}")
	public void updateCategory(
			@PathVariable("id") String productIdString,
			@RequestParam(value = "name", required = false) String name
	){
		int categoryId = Integer.parseInt(productIdString);
		Category updatedCategory = new Category(categoryId, name);
		service.update(categoryId, updatedCategory);
	}
	
	/*works*/
	@DeleteMapping("/deleteCategory/{id}")
	public void deleteCategory(@PathVariable("id") String categoryIdString) {
		int categoryId = Integer.parseInt(categoryIdString);
		service.delete(categoryId);
	}
}
