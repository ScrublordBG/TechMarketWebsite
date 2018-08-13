package com.techmark.techmarkwebsite.web.thymeleaf_controllers;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.services.base.CategoryService;
import com.techmark.techmarkwebsite.services.base.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductThymeleafController {
	private ProductService productService;
	private CategoryService categoryService;
	
	@Autowired
	public ProductThymeleafController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}
	
	@GetMapping("/search_products_by_category")
	public String aboutPage(@RequestParam("id") String idString, Model model) {
		model.addAttribute("header", "sections/site-navigation");
		List<Category> categories = categoryService.getAll();
		model.addAttribute("categories", categories);
		int id = Integer.parseInt(idString);
		List<Product> products = categoryService.getAllProductsById(id);
		model.addAttribute("products", products);
		
		model.addAttribute("view","products/search_products_by_category");
		return "base";
	}
}
