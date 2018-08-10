package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.base.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTests {
	@Mock
	CategoryRepository mockCategorySqlRepository;
	
	@InjectMocks
	CategoryServiceImpl service;
	
	@Test
	public void getAllProductsByCategoryId_Returns_AllProductsWithSetId(){
		int categoryId = 2;
		Mockito.when(mockCategorySqlRepository.getAllByCategoryId(categoryId))
				.thenReturn(Arrays.asList(
						new Product(1, "pd1", 100, "descr1", "url1", new Category(2, "cat2")),
						new Product(2, "pd2", 200, "descr2", "url2", new Category(2, "cat2")),
						new Product(3, "pd3", 300, "descr3", "url3", new Category(2, "cat2"))
				));
		
		List<Product> result = service.getAllProductsById(categoryId);
		
		Assert.assertEquals(3,result.size());
	}
	
	@Test
	public void getById_Returns_CategoryWithSameId(){
		int categoryId = 3;
		Mockito.when(mockCategorySqlRepository.getById(categoryId))
				.thenReturn(
						new Category(categoryId, "catDescr")
				);
		
		Category category = service.getById(categoryId);
		
		Assert.assertEquals(categoryId, category.getCategoryId());
	}
	
	@Test
	public void getAll_Returns_AllCategories(){
		Mockito.when(mockCategorySqlRepository.getAll())
				.thenReturn(Arrays.asList(
						new Category(1, "catDescr1"),
						new Category(2, "catDescr2"),
						new Category(3, "catDescr3")
				));
		
		List<Category> result = service.getAll();
		
		Assert.assertEquals(3,result.size());
	}
	
	@Test
	public void updateCategory_Returns_UpdatedCategory(){
		Category mockUpdatedCategory = new Category(1, "catDescr1");
		
		// Test that the mocked repository receives in its update method an integer in the first place and a Order class in the second
		doNothing().when(mockCategorySqlRepository).update(isA(Integer.class),isA(Category.class));
		
		// Tell the service to send via its update method 1 and mockUpdatedProduct
		service.update(1, mockUpdatedCategory);
		
		// Verify that the mocked repository receive as a one-time event the update method, where it should receive 1 and mockUpdatedProduct
		verify(mockCategorySqlRepository,times(1)).update(1,mockUpdatedCategory);
	}
	
	@Test
	public void createCategory_Returns_NewCategory(){
		Category mockNewCategory = new Category(1, "catDescr1");
		doNothing().when(mockCategorySqlRepository).create(isA(Category.class));
		service.create(mockNewCategory);
		
		verify(mockCategorySqlRepository,times(1)).create(mockNewCategory);
	}
	
	@Test
	public void deleteCategory_Returns_TheCategoryHasBeenDeleted(){
		doNothing().when(mockCategorySqlRepository).delete(isA(Integer.class));
		service.delete(1);
		
		verify(mockCategorySqlRepository,times(1)).delete(1);
	}
}
