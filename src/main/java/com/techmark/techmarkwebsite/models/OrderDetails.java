package com.techmark.techmarkwebsite.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "order details")
public class OrderDetails {
    
    private int orderID;
    private int productID;
    private int productPrice;
    private int quantity;

    public OrderDetails() {
    }

    public OrderDetails(int productID, int orderID, int productPrice, int quantity) {
        this.productID = productID;
        this.orderID = orderID;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
