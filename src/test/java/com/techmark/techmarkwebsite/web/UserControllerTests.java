package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.models.User;
import com.techmark.techmarkwebsite.services.base.GenericService;
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

import java.util.Arrays;
import java.util.List;

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
public class UserControllerTests {
	@MockBean
	GenericService<User> mockService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getAll_UserEntries_ShouldReturnStatus200AndAllUsers() throws Exception {
		List<User> users = Arrays.asList(
				new User(1, "fn1", "ln1", "un1", "pw1"),
				new User(2, "fn2", "ln2", "un2", "pw2"),
				new User(3, "fn3", "ln3", "un3", "pw3"),
				new User(4, "fn4", "ln4", "un4", "pw4")
		);
		
		Mockito.when(mockService.getAll())
				.thenReturn(users);
		
		ResultActions expect = mockMvc.perform(get("/users/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].userId").value(1))
				.andExpect(jsonPath("$[0].firstName").value("fn1"))
				.andExpect(jsonPath("$[0].lastName").value("ln1"))
				.andExpect(jsonPath("$[0].username").value("un1"))
				.andExpect(jsonPath("$[0].password").value("pw1"))
				.andExpect(jsonPath("$[1].userId").value(2))
				.andExpect(jsonPath("$[1].firstName").value("fn2"))
				.andExpect(jsonPath("$[1].lastName").value("ln2"))
				.andExpect(jsonPath("$[1].username").value("un2"))
				.andExpect(jsonPath("$[1].password").value("pw2"))
				.andExpect(jsonPath("$[2].userId").value(3))
				.andExpect(jsonPath("$[2].firstName").value("fn3"))
				.andExpect(jsonPath("$[2].lastName").value("ln3"))
				.andExpect(jsonPath("$[2].username").value("un3"))
				.andExpect(jsonPath("$[2].password").value("pw3"))
				.andExpect(jsonPath("$[3].userId").value(4))
				.andExpect(jsonPath("$[3].firstName").value("fn4"))
				.andExpect(jsonPath("$[3].lastName").value("ln4"))
				.andExpect(jsonPath("$[3].username").value("un4"))
				.andExpect(jsonPath("$[3].password").value("pw4"));
		
		
		verify(mockService, times(1)).getAll();
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void getById_ShouldReturnUserEntryWithSameId() throws Exception {
		User user = new User(1, "fn1", "ln1", "un1", "pw1");
		
		Mockito.when(mockService.getById(1))
				.thenReturn(user);
		
		mockMvc.perform(get("/users/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.userId").value(1))
				.andExpect(jsonPath("$.firstName").value("fn1"))
				.andExpect(jsonPath("$.lastName").value("ln1"))
				.andExpect(jsonPath("$.username").value("un1"))
				.andExpect(jsonPath("$.password").value("pw1"));
		
		verify(mockService, times(1)).getById(1);
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void add_NewUserEntry_ShouldAddUserEntryAndReturnAddedEntry() throws Exception {
		
		doNothing().when(mockService).create(isA(User.class));
		
		mockMvc.perform(post("/users/addUser?firstName={firstName}&lastName={lastName}&username={username}&password={password}", "fn1", "ln1", "un1", "pw1"))
				.andDo(print())
				.andExpect(status().isOk());
		
		ArgumentCaptor<User> dtoCaptor = ArgumentCaptor.forClass(User.class);
		verify(mockService, times(1)).create(dtoCaptor.capture());
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void update_UserEntry_ShouldUpdateUserEntryAndReturnUpdatedEntry() throws Exception {
		
		doNothing().when(mockService).update(isA(Integer.class),isA(User.class));
		
		mockMvc.perform(put("/users/updateUser/{userId}?firstName={firstName}&lastName={lastName}&username={username}&password={password}", 1, "fn1", "ln1", "un1", "pw1"))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockService, times(1)).update(isA(Integer.class), isA(User.class));
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void delete_UserEntry_ShouldDeleteUserEntryAndReturnDeletedEntry() throws Exception {
		
		doNothing().when(mockService).delete(isA(Integer.class));
		
		mockMvc.perform(delete("/users/deleteUser/{userId}",1))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockService, times(1)).delete(isA(Integer.class));
		verifyNoMoreInteractions(mockService);
	}
}
