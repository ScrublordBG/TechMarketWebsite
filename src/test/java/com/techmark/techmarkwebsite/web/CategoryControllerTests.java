package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.services.base.CategoryService;
import org.apache.logging.log4j.core.util.TypeUtil;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTests {
	@MockBean
	CategoryService mockService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getAll_whenCategory_shouldStatus200AndContainCategories() throws Exception {
		
		/**
		 * We can write an unit test for this controller method by following these steps:
		 *
		 * Create the test data which is returned when the getAll() method of the CategoryService interface is called.
		 * Configure our mock object to return the created test data when its getAll() method is invoked.
		 * Execute a GET request to url ‘/category/’.
		 * Verify that the HTTP status code 200 is returned.
		 * Verify that the content type of the response is ‘application/json’ and its character set is ‘UTF-8’.
		 * Get the collection of category entries by using the JsonPath expression $ and ensure that that three category entries are returned.
		 * Get the categoryId and name of the first category entry by using JsonPath expressions $[0].categoryId and $[0].name. Verify that the correct values are returned.
		 * Get the categoryId and name of the second category entry by using JsonPath expressions $[1].categoryId and $[1].name. Verify that the correct values are returned.
		 * Verify that the getAll() method of the CategoryService interface is called only once.
		 * Ensure that no other methods of our mock object are called during the test.
		 */
		List<Category> categories = Arrays.asList(
				new Category(1, "Televisions"),
				new Category(2, "Smartphones"),
				new Category(3, "Tablets")
		);
		
		Mockito.when(mockService.getAll())
				.thenReturn(categories);
		
		ResultActions expect = mockMvc.perform(get("/categories/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].categoryId").value(1))
				.andExpect(jsonPath("$[0].name").value("Televisions"))
				.andExpect(jsonPath("$[1].categoryId").value(2))
				.andExpect(jsonPath("$[1].name").value("Smartphones"))
				.andExpect(jsonPath("$[2].categoryId").value(3))
				.andExpect(jsonPath("$[2].name").value("Tablets"));
		
		verify(mockService, times(1)).getAll();
		verifyNoMoreInteractions(mockService);
		
		/*categories
				.forEach(category ->
				{
					try {
						expect.andExpect(
								content()
										.string(containsString(category.getName()))
						);
					} catch (Exception e) {
						Assert.fail();
					}
				});*/
	}
	
	@Test
	public void getById_ShouldReturnCategoryEntryWithSameId() throws Exception {
		
		Mockito.when(mockService.getById(1))
				.thenReturn(new Category(1, "Televisions"));
		
		mockMvc.perform(get("/categories/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.categoryId").value(1))
				.andExpect(jsonPath("$.name").value("Televisions"));
		
		verify(mockService, times(1)).getById(1);
		verifyNoMoreInteractions(mockService);
	}
}
