package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTests {
	
	@Mock
	GenericRepository<Product> mockProductSqlRepository;
	
	@InjectMocks
	ProductServiceImpl service;
	
	@Test
	public void getById_Returns_ProductWithSameId(){
		int productId = 3;
		Mockito.when(mockProductSqlRepository.getById(productId))
				.thenReturn(
						new Product(productId, "pd2", 2, "descr2", "url2", new Category(1, "cat1"))
				);
		
		Product product = service.getById(productId);
		
		Assert.assertEquals(productId, product.getProductId());
	}
	
	@Test
	public void getAll_Returns_AllProducts(){
		Mockito.when(mockProductSqlRepository.getAll())
				.thenReturn(Arrays.asList(
						new Product(1, "pd1", 100, "descr1", "url1", new Category(1, "cat1")),
						new Product(2, "pd2", 200, "descr2", "url2", new Category(2, "cat2")),
						new Product(3, "pd3", 300, "descr3", "url3", new Category(3, "cat3"))
				));
		
		List<Product> result = service.getAll();
		
		Assert.assertEquals(3,result.size());
	}
	
	@Test
	public void updateProduct_Returns_UpdatedProduct(){
		Product mockUpdatedProduct = new Product(2, "pd2", 2, "descr2", "url2", new Category(1, "cat1"));
		
		// Test that the mocked repository receives in its update method an integer in the first place and a User class in the second
		doNothing().when(mockProductSqlRepository).update(isA(Integer.class),isA(Product.class));
		
		// Tell the service to send via its update method 1 and mockUpdatedProduct
		service.update(2, mockUpdatedProduct);
		
		// Verify that the mocked repository receive as a one-time event the update method, where it should receive 1 and mockUpdatedProduct
		verify(mockProductSqlRepository,times(1)).update(2,mockUpdatedProduct);
	}
	
	@Test
	public void createProduct_Returns_NewProduct(){
		Product mockNewProduct = new Product(2, "pd2", 2, "descr2", "url2", new Category(1, "cat1"));
		doNothing().when(mockProductSqlRepository).create(isA(Product.class));
		service.create(mockNewProduct);
		
		verify(mockProductSqlRepository,times(1)).create(mockNewProduct);
	}
	
	@Test
	public void deleteProduct_Returns_TheProductHasBeenDeleted(){
		doNothing().when(mockProductSqlRepository).delete(isA(Integer.class));
		service.delete(1);
		
		verify(mockProductSqlRepository,times(1)).delete(1);
	}
}
