package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailId;
import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.models.OrderDetail;
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

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {
	@MockBean
	GenericService<Order> mockService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getAll_OrderEntries_ShouldReturnStatus200AndAllOrders() throws Exception {
		Date orderDate1 = new GregorianCalendar(2018, Calendar.AUGUST, 3).getTime();
		Date orderDate2 = new GregorianCalendar(2018, Calendar.JULY, 10).getTime();
		Date orderDate3 = new GregorianCalendar(2018, Calendar.APRIL, 21).getTime();
		User user1 = new User(1, "fn1", "ln1", "un1", "pw1");
		User user2 = new User(2, "fn2", "ln2", "un2", "pw2");
		User user3 = new User(3, "fn3", "ln3", "un3", "pw3");
		
		List<Order> orders = Arrays.asList(
				new Order(10, user1, orderDate1),
				new Order(20, user2, orderDate2),
				new Order(30, user3, orderDate3)
		);
		
		Mockito.when(mockService.getAll())
				.thenReturn(orders);
		
		ResultActions expect = mockMvc.perform(get("/orders/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].orderId").value(10))
				.andExpect(jsonPath("$[0].userId").value(1))
				.andExpect(jsonPath("$[0].date").value("03-08-2018"))
				.andExpect(jsonPath("$[1].orderId").value(20))
				.andExpect(jsonPath("$[1].userId").value(2))
				.andExpect(jsonPath("$[1].date").value("10-07-2018"))
				.andExpect(jsonPath("$[2].orderId").value(30))
				.andExpect(jsonPath("$[2].userId").value(3))
				.andExpect(jsonPath("$[2].date").value("21-04-2018"));
		
		verify(mockService, times(1)).getAll();
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void getById_ShouldReturnOrderEntryWithSameId() throws Exception {
		User user = new User(1, "fn1", "ln1", "un1", "pw1");
		Date date = new GregorianCalendar(2018, Calendar.JANUARY, 20).getTime();
		Order order = new Order(1, user, date);
		
		Mockito.when(mockService.getById(1))
				.thenReturn(order);
		
		mockMvc.perform(get("/orders/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.orderId").value(1))
				.andExpect(jsonPath("$.userId").value(1))
				.andExpect(jsonPath("$.date").value("20-01-2018"));
		
		verify(mockService, times(1)).getById(1);
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void add_NewOrderEntry_ShouldAddOrderEntryAndReturnAddedEntry() throws Exception {
		
		doNothing().when(mockService).create(isA(Order.class));
		
		mockMvc.perform(post("/orders/addOrder?userId={userId}&date={date}", 1, "20-02-2017"))
				.andDo(print())
				.andExpect(status().isOk());
		
		ArgumentCaptor<Order> dtoCaptor = ArgumentCaptor.forClass(Order.class);
		verify(mockService, times(1)).create(dtoCaptor.capture());
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void update_OrderEntry_ShouldUpdateOrderEntryAndReturnUpdatedEntry() throws Exception {
		
		doNothing().when(mockService).update(isA(Integer.class),isA(Order.class));
		
		mockMvc.perform(put("/orders/updateOrder/{orderId}?userId={userId}&date={date}", 1, 2, "20-03-2018"))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockService, times(1)).update(isA(Integer.class), isA(Order.class));
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void delete_OrderEntry_ShouldDeleteOrderEntryAndReturnDeletedEntry() throws Exception {
		
		doNothing().when(mockService).delete(isA(Integer.class));
		
		mockMvc.perform(delete("/orders/deleteOrder/{orderId}",1))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockService, times(1)).delete(isA(Integer.class));
		verifyNoMoreInteractions(mockService);
	}
}
