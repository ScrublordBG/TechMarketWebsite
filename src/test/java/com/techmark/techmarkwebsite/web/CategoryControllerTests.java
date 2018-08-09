package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.services.base.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		List<Category> categories = Arrays.asList(
				new Category(1, "Televisions"),
				new Category(2, "Smartphones"),
				new Category(3, "Tablets")
		);
		
		Mockito.when(mockService.getAll())
				.thenReturn(categories);
		
		ResultActions expect = mockMvc.perform(
				get("/categories/")
				)
				.andDo(print())
				.andExpect(status().isOk());
		
		categories
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
				});
		
	}
}
