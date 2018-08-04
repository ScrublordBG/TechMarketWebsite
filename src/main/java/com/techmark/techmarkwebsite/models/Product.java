package com.techmark.techmarkwebsite.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.techmark.techmarkwebsite.serializers.ProductSerializer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@JsonSerialize(using = ProductSerializer.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int productId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private int price;

    @Column(name = "Description")
    private String description;

    @Column(name = "ImageURL")
    private String imageURL;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryID")
		@JsonManagedReference
    private Category category;
	
		@OneToMany(mappedBy = "product")
		private List<OrderDetails> orderDetails;
	
    public Product() {
    }

    public Product(int productId, String name, int price, String description, String imageURL, Category category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
        this.category = category;
    }
    
    public Product(String name, int price, String description, String imageURL, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
	
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
