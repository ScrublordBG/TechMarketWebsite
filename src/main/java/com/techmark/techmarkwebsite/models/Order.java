package com.techmark.techmarkwebsite.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private int orderID;
    
    @Column(name = "UserID")
    private int userID;
    
    @Column(name = "OrderDate")
    private Date date;

    public Order() {
    }

    public Order(int orderID, int userID, Date date) {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
