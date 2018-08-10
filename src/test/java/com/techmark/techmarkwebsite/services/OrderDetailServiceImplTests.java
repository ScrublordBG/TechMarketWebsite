package com.techmark.techmarkwebsite.services;

import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailId;
import com.techmark.techmarkwebsite.models.OrderDetail;
import com.techmark.techmarkwebsite.repositories.base.OrderDetailRepository;
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
public class OrderDetailServiceImplTests {
	@Mock
	OrderDetailRepository mockOrderDetailSqlRepository;
	
	@InjectMocks
	OrderDetailServiceImpl service;
	
	@Test
	public void getById_Returns_OrderDetailWithThisOrderDetailId(){
		int orderId = 3;
		int productId = 10;
		OrderDetailId orderDetailId = new OrderDetailId(orderId, productId);
		
		Mockito.when(mockOrderDetailSqlRepository.getById(orderDetailId))
				.thenReturn(
						new OrderDetail(new OrderDetailId(3, 10), 100, 5)
				);
		
		OrderDetail orderDetail = service.getById(orderDetailId);
		
		Assert.assertEquals(orderId, orderDetail.getOrderDetailId().getOrderId());
		Assert.assertEquals(productId, orderDetail.getOrderDetailId().getProductId());
		Assert.assertEquals(100, orderDetail.getProductPrice());
		Assert.assertEquals(5, orderDetail.getQuantity());
	}
	
	@Test
	public void getAll_Returns_AllOrderDetails(){
		Mockito.when(mockOrderDetailSqlRepository.getAll())
				.thenReturn(Arrays.asList(
						new OrderDetail(new OrderDetailId(3, 10), 100, 5),
						new OrderDetail(new OrderDetailId(4, 2), 20, 1),
						new OrderDetail(new OrderDetailId(5, 10), 100, 2),
						new OrderDetail(new OrderDetailId(1, 16), 55, 4)
				));
		
		List<OrderDetail> result = service.getAll();
		
		Assert.assertEquals(4,result.size());
	}
	
	@Test
	public void updateOrderDetail_Returns_UpdatedOrderDetail(){
		OrderDetail mockUpdatedOrderDetail = new OrderDetail(new OrderDetailId(1, 2), 10, 5);
		
		// Test that the mocked repository receives in its update method an OrderDetailId class in the first place and an OrderDetail class in the second
		doNothing().when(mockOrderDetailSqlRepository).update(isA(OrderDetailId.class),isA(OrderDetail.class));
		
		// Tell the service to send via its update method an id with orderId = 1 and productId = 2, and mockUpdatedOrderDetail
		service.update(new OrderDetailId(1, 2), mockUpdatedOrderDetail);
		
		// Verify that the mocked repository receive as a one-time event the update method, where it should receive an order detail id with 1 for orderId and 2 for productId and mockUpdatedOrderDetail
		verify(mockOrderDetailSqlRepository,times(1)).update(new OrderDetailId(1, 2),mockUpdatedOrderDetail);
	}
	
	@Test
	public void createOrderDetail_Returns_NewOrderDetail(){
		OrderDetail mockNewOrderDetail = new OrderDetail(new OrderDetailId(1, 2), 3, 4);
		doNothing().when(mockOrderDetailSqlRepository).create(isA(OrderDetail.class));
		service.create(mockNewOrderDetail);
		
		verify(mockOrderDetailSqlRepository,times(1)).create(mockNewOrderDetail);
	}
	
	@Test
	public void deleteOrderDetail_Returns_TheOrderDetailHasBeenDeleted(){
		doNothing().when(mockOrderDetailSqlRepository).delete(isA(OrderDetailId.class));
		service.delete(new OrderDetailId(1, 2));
		
		verify(mockOrderDetailSqlRepository,times(1)).delete(new OrderDetailId(1, 2));
	}
}
