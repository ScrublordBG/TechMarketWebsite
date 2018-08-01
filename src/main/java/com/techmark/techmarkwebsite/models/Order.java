package com.techmark.techmarkwebsite.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private int orderID;
    
    // Problem: @JsonIgnore makes so that this property (and column) - UserID - is missing from
		// json response when we want to view order(s)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
		//@JsonManagedReference
    private User userID;
    
    @Column(name = "OrderDate")
    private Date date;

    public Order() {
    }

    public Order(int orderID, User userID, Date date) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
