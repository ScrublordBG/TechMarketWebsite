package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.services.base.GenericService;
import com.techmark.techmarkwebsite.services.base.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {
	@MockBean
	ProductService mockService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getAll_ProductEntries_ShouldReturnStatus200AndAllProducts() throws Exception {
		List<Product> products = Arrays.asList(
				new Product(1, "product1", 10, "product_description1", "product_image1", new Category(1, "category1")),
				new Product(2, "product2", 20, "product_description2", "product_image2", new Category(2, "category2")),
				new Product(3, "product3", 30, "product_description3", "product_image3", new Category(3, "category3"))
		);
		
		Mockito.when(mockService.getAll())
				.thenReturn(products);
		
		ResultActions expect = mockMvc.perform(get("/products/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("product1"))
				.andExpect(jsonPath("$[0].price").value(10))
				.andExpect(jsonPath("$[0].description").value("product_description1"))
				.andExpect(jsonPath("$[0].imageUrl").value("product_image1"))
				.andExpect(jsonPath("$[0].category.categoryId").value(1))
				.andExpect(jsonPath("$[0].category.categoryName").value("category1"))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].name").value("product2"))
				.andExpect(jsonPath("$[1].price").value(20))
				.andExpect(jsonPath("$[1].description").value("product_description2"))
				.andExpect(jsonPath("$[1].imageUrl").value("product_image2"))
				.andExpect(jsonPath("$[1].category.categoryId").value(2))
				.andExpect(jsonPath("$[1].category.categoryName").value("category2"))
				.andExpect(jsonPath("$[2].id").value(3))
				.andExpect(jsonPath("$[2].name").value("product3"))
				.andExpect(jsonPath("$[2].price").value(30))
				.andExpect(jsonPath("$[2].description").value("product_description3"))
				.andExpect(jsonPath("$[2].imageUrl").value("product_image3"))
				.andExpect(jsonPath("$[2].category.categoryId").value(3))
				.andExpect(jsonPath("$[2].category.categoryName").value("category3"));
				
		
		verify(mockService, times(1)).getAll();
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void getById_ShouldReturnProductEntryWithSameId() throws Exception {
		Product product = new Product(1, "product1", 10, "product_description1", "product_image1", new Category(1, "category1"));
		
		Mockito.when(mockService.getById(1))
				.thenReturn(product);
		
		mockMvc.perform(get("/products/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("product1"))
				.andExpect(jsonPath("$.price").value(10))
				.andExpect(jsonPath("$.description").value("product_description1"))
				.andExpect(jsonPath("$.imageUrl").value("product_image1"))
				.andExpect(jsonPath("$.category.categoryId").value(1))
				.andExpect(jsonPath("$.category.categoryName").value("category1"));
		
		verify(mockService, times(1)).getById(1);
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void add_NewProductEntry_ShouldAddProductEntryAndReturnAddedEntry() throws Exception {
		
		doNothing().when(mockService).create(isA(Product.class));
		
		mockMvc.perform(post("/products/addProduct?name={name}&price={price}&description={description}&imageUrl={imageUrl}&categoryId={categoryId}&categoryName={categoryName}", "product1", 10, "product_description1", "product_image1", 1, "categoryName1"))
				.andDo(print())
				.andExpect(status().isOk());
		
		ArgumentCaptor<Product> dtoCaptor = ArgumentCaptor.forClass(Product.class);
		verify(mockService, times(1)).create(dtoCaptor.capture());
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void update_ProductEntry_ShouldUpdateProductEntryAndReturnUpdatedEntry() throws Exception {
		
		doNothing().when(mockService).update(isA(Integer.class),isA(Product.class));
		
		mockMvc.perform(put("/products/updateProduct/{productId}?name={name}&price={price}&description={description}&imageUrl={imageUrl}&categoryId={categoryId}&categoryName={categoryName}", 1, "product1", 10, "product_description1", "product_image1", 1, "category1"))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockService, times(1)).update(isA(Integer.class), isA(Product.class));
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void delete_ProductEntry_ShouldDeleteProductEntryAndReturnDeletedEntry() throws Exception {
		
		doNothing().when(mockService).delete(isA(Integer.class));
		
		mockMvc.perform(delete("/products/deleteProduct/{productId}",1))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockService, times(1)).delete(isA(Integer.class));
		verifyNoMoreInteractions(mockService);
	}
}
