package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailId;
import com.techmark.techmarkwebsite.models.OrderDetail;
import com.techmark.techmarkwebsite.services.base.OrderDetailService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderDetailControllerTests {
	@MockBean
	OrderDetailService mockService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getAll_OrderDetailEntries_ShouldReturnStatus200AndAllOrderDetails() throws Exception {
		
		List<OrderDetail> orderDetailList = Arrays.asList(
				new OrderDetail(new OrderDetailId(1, 2), 20, 2),
				new OrderDetail(new OrderDetailId(1, 3), 10, 5),
				new OrderDetail(new OrderDetailId(2, 2), 20, 2)
		);
		
		Mockito.when(mockService.getAll())
				.thenReturn(orderDetailList);
		
		ResultActions expect = mockMvc.perform(get("/order_details/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].orderDetailId.orderId").value(1))
				.andExpect(jsonPath("$[0].orderDetailId.productId").value(2))
				.andExpect(jsonPath("$[0].productPrice").value(20))
				.andExpect(jsonPath("$[0].quantity").value(2))
				.andExpect(jsonPath("$[1].orderDetailId.orderId").value(1))
				.andExpect(jsonPath("$[1].orderDetailId.productId").value(3))
				.andExpect(jsonPath("$[1].productPrice").value(10))
				.andExpect(jsonPath("$[1].quantity").value(5))
				.andExpect(jsonPath("$[2].orderDetailId.orderId").value(2))
				.andExpect(jsonPath("$[2].orderDetailId.productId").value(2))
				.andExpect(jsonPath("$[2].productPrice").value(20))
				.andExpect(jsonPath("$[2].quantity").value(2));
		
		verify(mockService, times(1)).getAll();
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void getById_ShouldReturnOrderDetailEntryWithSameId() throws Exception {
		OrderDetailId orderDetailId = new OrderDetailId(1, 2);
		OrderDetail orderDetail = new OrderDetail(orderDetailId, 20, 2);
		
		Mockito.when(mockService.getById(orderDetailId))
				.thenReturn(orderDetail);
		
		mockMvc.perform(get("/order_details/getOrderDetailByOrderDetailId?orderId={orderId}&productId={productId}", 1, 2))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.orderDetailId.orderId").value(1))
				.andExpect(jsonPath("$.orderDetailId.productId").value(2))
				.andExpect(jsonPath("$.productPrice").value(20))
				.andExpect(jsonPath("$.quantity").value(2));
		
		verify(mockService, times(1)).getById(orderDetailId);
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void add_NewOrderDetailEntry_ShouldAddOrderDetailEntryAndReturnAddedEntry() throws Exception {
		
		doNothing().when(mockService).create(isA(OrderDetail.class));
		
		mockMvc.perform(post("/order_details/addOrderDetail?orderId={orderId}&productId={productId}&productPrice={productPrice}&quantity={quantity}", 1, 2, 30, 2))
				.andDo(print())
				.andExpect(status().isOk());
		
		ArgumentCaptor<OrderDetail> dtoCaptor = ArgumentCaptor.forClass(OrderDetail.class);
		verify(mockService, times(1)).create(dtoCaptor.capture());
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void update_OrderDetailEntry_ShouldUpdateOrderDetailEntryAndReturnUpdatedEntry() throws Exception {
		
		doNothing().when(mockService).update(isA(OrderDetailId.class),isA(OrderDetail.class));
		
		mockMvc.perform(put("/order_details/updateOrderDetail?orderId={orderId}&productId={productId}&productPrice={productPrice}&quantity={quantity}", 1, 2, 30, 2))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockService, times(1)).update(isA(OrderDetailId.class), isA(OrderDetail.class));
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void delete_OrderDetailEntry_ShouldDeleteOrderDetailEntryAndReturnDeletedEntry() throws Exception {
		
		doNothing().when(mockService).delete(isA(OrderDetailId.class));
		
		mockMvc.perform(delete("/order_details/deleteOrderDetail?orderId={orderId}&productId={productId}",1, 2))
				.andDo(print())
				.andExpect(status().isOk());
		
		verify(mockService, times(1)).delete(isA(OrderDetailId.class));
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void getAllOrderDetailsListByOrderId_ShouldReturnAllOrderDetailsWithGivenOrderId() throws Exception {
		List<OrderDetail> orderDetailList = Arrays.asList(
				new OrderDetail(new OrderDetailId(1, 2), 20, 2),
				new OrderDetail(new OrderDetailId(1, 3), 10, 5),
				new OrderDetail(new OrderDetailId(1, 4), 30, 2)
		);
		
		Mockito.when(mockService.getAllByOrderId(1))
				.thenReturn(orderDetailList);
		
		ResultActions expect = mockMvc.perform(get("/order_details/getAllOrderDetailsListByOrderId/{id}", 1))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].orderDetailId.orderId").value(1))
				.andExpect(jsonPath("$[0].orderDetailId.productId").value(2))
				.andExpect(jsonPath("$[0].productPrice").value(20))
				.andExpect(jsonPath("$[0].quantity").value(2))
				.andExpect(jsonPath("$[1].orderDetailId.orderId").value(1))
				.andExpect(jsonPath("$[1].orderDetailId.productId").value(3))
				.andExpect(jsonPath("$[1].productPrice").value(10))
				.andExpect(jsonPath("$[1].quantity").value(5))
				.andExpect(jsonPath("$[2].orderDetailId.orderId").value(1))
				.andExpect(jsonPath("$[2].orderDetailId.productId").value(4))
				.andExpect(jsonPath("$[2].productPrice").value(30))
				.andExpect(jsonPath("$[2].quantity").value(2));
		
		verify(mockService, times(1)).getAllByOrderId(1);
		verifyNoMoreInteractions(mockService);
	}
}
