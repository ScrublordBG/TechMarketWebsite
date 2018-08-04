package com.techmark.techmarkwebsite.models;

import com.techmark.techmarkwebsite.models.Embeddables.OrderDetailsId;

import javax.persistence.*;

@Entity
@Table(name = "order details")
public class OrderDetails {
    
    @EmbeddedId
    protected OrderDetailsId orderDetailsId;
    
    @ManyToOne
    @JoinColumn(
        name = "OrderID",
        insertable = false,
        updatable = false
    )
    protected Order order;
    
    @ManyToOne
    @JoinColumn(
        name = "ProductID",
        insertable = false,
        updatable = false
    )
    protected Product product;
    
    @Column(name = "ProductPrice")
    private int productPrice;
    
    @Column(name = "Quantity")
    private int quantity;

    public OrderDetails() {
    }

    /*public OrderDetails(OrderDetailsId id) {
        this.orderDetailsId = id;
    }*/
    
    public OrderDetails(OrderDetailsId id, int productPrice, int quantity) {
        this.orderDetailsId = id;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
    
    public OrderDetailsId getOrderDetailsId() {
        return orderDetailsId;
    }
    
    public void setOrderDetailsId(OrderDetailsId orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
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
}
