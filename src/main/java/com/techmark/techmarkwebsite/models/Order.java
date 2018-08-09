package com.techmark.techmarkwebsite.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.techmark.techmarkwebsite.serializers.JsonDateSerializer;
import com.techmark.techmarkwebsite.serializers.OrderSerializer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@JsonSerialize(using = OrderSerializer.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private int orderId;
    
    // @JsonIgnore makes so that info coming from related object (here, User) is not transferred and visualized within the json response when we want to view order(s). In other words, ignores info coming from other mapped classes (here - User class). Could be also managed using @JsonManagedReference and in the case of mappedBy - using @JsonBackReference. We can manage this also with json serializers.
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
		@JsonManagedReference
    private User user;
    
    @OneToMany(
    		fetch = FetchType.EAGER, /* must be of type EAGER or else we cannot execute get requests to find all order details for a given order id*/
    		mappedBy = "order",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
		@JsonBackReference
		private List<OrderDetail> orderDetailList;
    
    @Column(name = "OrderDate")
    private Date date;
    
    public Order() {
    
		}

    public Order(int orderId, User user, Date date) {
        this.orderId = orderId;
        this.user = user;
        this.date = date;
    }
	
		public Order(User user, Date date) {
			this.user = user;
			this.date = date;
		}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	
    /*formats date to as specifically defined date format*/
		@JsonSerialize(using= JsonDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
	
		public List<OrderDetail> getOrderDetailList() {
			return orderDetailList;
		}
		
		/*public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
			this.orderDetailsList = orderDetailsList;
		}*/
	
		// additionally added (check if work and are necessary; if not, delete these two methods)
		// used for deleting/ adding (updating) orderDetails from/ to an order
		public void addOrderDetail(OrderDetail orderDetail) {
			this.orderDetailList.add(orderDetail);
			orderDetail.setOrder(this);
		}
		
		public void removeOrderDetail(OrderDetail orderDetail) {
			orderDetail.setOrder(null);
			this.orderDetailList.remove(orderDetail);
		}
}
