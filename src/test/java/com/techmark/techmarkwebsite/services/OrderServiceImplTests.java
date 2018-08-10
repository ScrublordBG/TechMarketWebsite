package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.models.User;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTests {
	@Mock
	GenericRepository<Order> mockOrderSqlRepository;
	
	@InjectMocks
	OrderServiceImpl service;
	
	@Test
	public void getById_Returns_OrderWithSameId(){
		int orderId = 3;
		Date orderDate = new GregorianCalendar(2018, Calendar.AUGUST, 3).getTime();
		Mockito.when(mockOrderSqlRepository.getById(orderId))
				.thenReturn(
						new Order(orderId, new User(), orderDate)
				);
		
		Order order = service.getById(orderId);
		
		Assert.assertEquals(orderId, order.getOrderId());
	}
	
	@Test
	public void getAll_Returns_AllOrders(){
		Mockito.when(mockOrderSqlRepository.getAll())
				.thenReturn(Arrays.asList(
						new Order(1, new User(), new GregorianCalendar(2018, Calendar.JANUARY, 30).getTime()),
						new Order(2, new User(), new GregorianCalendar(2018, Calendar.FEBRUARY, 20).getTime()),
						new Order(3, new User(), new GregorianCalendar(2018, Calendar.MARCH, 30).getTime())
				));
		
		List<Order> result = service.getAll();
		
		Assert.assertEquals(3,result.size());
	}
	
	@Test
	public void updateOrder_Returns_UpdatedOrder(){
		Order mockUpdatedOrder = new Order(1, new User(), new GregorianCalendar(2018, Calendar.JANUARY, 30).getTime());
		
		// Test that the mocked repository receives in its update method an integer in the first place and a Order class in the second
		doNothing().when(mockOrderSqlRepository).update(isA(Integer.class),isA(Order.class));
		
		// Tell the service to send via its update method 1 and mockUpdatedProduct
		service.update(1, mockUpdatedOrder);
		
		// Verify that the mocked repository receive as a one-time event the update method, where it should receive 1 and mockUpdatedProduct
		verify(mockOrderSqlRepository,times(1)).update(1,mockUpdatedOrder);
	}
	
	@Test
	public void createOrder_Returns_NewOrder(){
		Order mockNewOrder = new Order(1, new User(), new GregorianCalendar(2018, Calendar.JANUARY, 30).getTime());
		doNothing().when(mockOrderSqlRepository).create(isA(Order.class));
		service.create(mockNewOrder);
		
		verify(mockOrderSqlRepository,times(1)).create(mockNewOrder);
	}
	
	@Test
	public void deleteOrder_Returns_TheOrderHasBeenDeleted(){
		doNothing().when(mockOrderSqlRepository).delete(isA(Integer.class));
		service.delete(1);
		
		verify(mockOrderSqlRepository,times(1)).delete(1);
	}
}
