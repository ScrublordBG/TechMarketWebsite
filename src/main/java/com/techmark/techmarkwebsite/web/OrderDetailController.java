package com.techmark.techmarkwebsite.web;

import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailId;
import com.techmark.techmarkwebsite.models.OrderDetail;
import com.techmark.techmarkwebsite.services.base.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order_details")
public class OrderDetailController {
	private OrderDetailService service;
	
	@Autowired
	public OrderDetailController(OrderDetailService service) {
		this.service = service;
	}
	
	/*works*/
	@GetMapping("/getAllOrderDetailsListByOrderId/{id}")
	public List<OrderDetail> getAllOrderDetailsListByOrderId(@PathVariable("id") String idString) {
		int id = Integer.parseInt(idString);
		return service.getAllByOrderId(id);
	}
	
	@GetMapping("/getOrderDetailByOrderDetailId")
	public OrderDetail getById(
			@RequestParam(value = "orderId") String orderIdString,
			@RequestParam(value = "productId") String productIdString) {
		int orderId = Integer.parseInt(orderIdString);
		int productId = Integer.parseInt(productIdString);
		OrderDetailId orderDetailId = new OrderDetailId(orderId, productId);
		return service.getById(orderDetailId);
	}
	
	@GetMapping("/")
	public List<OrderDetail> getAllOrderDetails() { return service.getAll();	}
	
	/*works*/
	@PostMapping("/addOrderDetail")
	public void createOrderDetail(
			@RequestParam(value = "orderId") String orderIdString,
			@RequestParam(value = "productId") String productIdString,
			@RequestParam(value = "productPrice") String productPriceString,
			@RequestParam(value = "quantity") String quantityString) {
		int orderId = Integer.parseInt(orderIdString);
		int productId = Integer.parseInt(productIdString);
		int productPrice = Integer.parseInt(productPriceString);
		int quantity = Integer.parseInt(quantityString);
		OrderDetailId orderDetailId = new OrderDetailId(orderId, productId);
		OrderDetail newOrderDetail = new OrderDetail(orderDetailId, productPrice, quantity);
		service.create(newOrderDetail);
	}
	
	/*works*/
	@PutMapping("/updateOrderDetail")
	public void updateOrderDetail(
			@RequestParam(value = "orderId") String orderIdString,
			@RequestParam(value = "productId") String productIdString,
			@RequestParam(value = "productPrice") String productPriceString,
			@RequestParam(value = "quantity") String quantityString
	){
		int orderId = Integer.parseInt(orderIdString);
		int productId = Integer.parseInt(productIdString);
		int productPrice = Integer.parseInt(productPriceString);
		int quantity = Integer.parseInt(quantityString);
		OrderDetailId orderDetailId = new OrderDetailId(orderId, productId);
		OrderDetail updatedOrderDetail = new OrderDetail(orderDetailId, productPrice, quantity);
		service.update(orderDetailId, updatedOrderDetail);
	}
	
	/*works*/
	@DeleteMapping("/deleteOrderDetail")
	public void deleteOrderDetail(
			@RequestParam(value = "orderId") String orderIdString,
			@RequestParam(value = "productId") String productIdString) {
		int orderId = Integer.parseInt(orderIdString);
		int productId = Integer.parseInt(productIdString);
		OrderDetailId orderDetailId = new OrderDetailId(orderId, productId);
		service.delete(orderDetailId);
	}
}
