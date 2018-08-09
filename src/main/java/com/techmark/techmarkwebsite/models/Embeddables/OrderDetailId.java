package com.techmark.techmarkwebsite.models.Embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailId implements Serializable {

	@Column(name = "OrderID")
	protected int orderId;
	
	@Column(name = "ProductID")
	protected int productId;
	
	protected OrderDetailId() {
	}
	
	public OrderDetailId(int orderId, int productId) {
		this.orderId = orderId;
		this.productId = productId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		
		if (o == null || getClass() != o.getClass()) { return false; }
		OrderDetailId that = (OrderDetailId) o;
		if (orderId != that.orderId) { return false; }
		if (productId != that.productId) { return false; }
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getOrderId(), getProductId());
	}
}
