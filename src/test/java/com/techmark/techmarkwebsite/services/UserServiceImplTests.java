package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.*;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {
	
	@Mock
	GenericRepository<User> mockUserSqlRepository;
	
	@InjectMocks
	UserServiceImpl service;
	
	@Test
	public void GetById_Returns_UserWithSameId(){
		int userId = 2;
		Mockito.when(mockUserSqlRepository.getById(userId))
				.thenReturn(new User(2,"fn2", "ln2","un2","pw2"));
		
		User result = service.getById(userId);
		
		Assert.assertEquals(2,result.getUserId());
	}
	
	@Test
	public void GetAll_Returns_AllUsers(){
		Mockito.when(mockUserSqlRepository.getAll())
				.thenReturn(Arrays.asList(
						new User(1,"fn1", "ln1","un1","pw1"),
						new User(2,"fn2", "ln2","un2","pw2"),
						new User(3,"fn3", "ln3","un3","pw3")
				));
		
		List<User> result = service.getAll();
		
		Assert.assertEquals(3,result.size());
	}
	
	@Test
	public void UpdateUser_Returns_UpdatedUser(){
		User mockUpdatedUser = new User(1,"new_fn1","new_ln1","new_un1","new_pw1");
		
		// Test that the mocked repository receives in its update method an integer in the first place and a User class in the second
		doNothing().when(mockUserSqlRepository).update(isA(Integer.class),isA(User.class));
		
		// Tell the service to send via its update method 1 and mockUpdatedUser
		service.update(1, mockUpdatedUser);
		
		// Verify that the mocked repository receive as a one-time event the update method, where it should receive 1 and mockUpdatedUser
		verify(mockUserSqlRepository,times(1)).update(1,mockUpdatedUser);
	}
	
	@Test
	public void CreateUser_Returns_NewUser(){
		User mockNewUser = new User(1,"new_fn1","new_ln1","new_un1","new_pw1");
		doNothing().when(mockUserSqlRepository).create(isA(User.class));
		service.create(mockNewUser);
		
		verify(mockUserSqlRepository,times(1)).create(mockNewUser);
	}
	
	@Test
	public void DeleteUser_Returns_TheUserHasBeenDeleted(){
		//User mockUser = new User(1,"new_fn1","new_ln1","new_un1","new_pw1");
		doNothing().when(mockUserSqlRepository).delete(isA(Integer.class));
		service.delete(1);
		
		verify(mockUserSqlRepository,times(1)).delete(1);
	}
}
