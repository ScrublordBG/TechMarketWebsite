package com.techmark.techmarkwebsite.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailId;
import com.techmark.techmarkwebsite.serializers.OrderDetailSerializer;


import javax.persistence.*;

@Entity
@Table(name = "order_details")
@JsonSerialize(using = OrderDetailSerializer.class)
public class OrderDetail {
    
    @EmbeddedId
    protected OrderDetailId orderDetailId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "OrderID",
        insertable = false,
        updatable = false
    )
    @JsonManagedReference
    protected Order order;
    
    @ManyToOne
    @JoinColumn(
        name = "ProductID",
        insertable = false,
        updatable = false
    )
    @JsonManagedReference
    protected Product product;
    
    @Column(name = "ProductPrice")
    private int productPrice;
    
    @Column(name = "Quantity")
    private int quantity;

    public OrderDetail() {
    }

    /*public OrderDetails(OrderDetailsId id) {
        this.orderDetailsId = id;
    }*/
    
    public OrderDetail(OrderDetailId id, int productPrice, int quantity) {
        this.orderDetailId = id;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
    
    public OrderDetailId getOrderDetailId() {
        return orderDetailId;
    }
    
    public void setOrderDetailId(OrderDetailId orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
    
    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
	
		public void setOrder(Order order) {
			this.order = order;
		}
    
    @Override
    public String toString() {
        return "OrderDetail: " +
            "orderId = " + orderDetailId.getOrderId() +
            "productId = " + orderDetailId.getProductId() +
            ", productPrice = " + productPrice +
            ", quantity = " + quantity;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof OrderDetail) {
            OrderDetail orderDetail = (OrderDetail) object;
            return this.getOrderDetailId().equals(orderDetail.getOrderDetailId());
        }
        
        return false;
    }
}
