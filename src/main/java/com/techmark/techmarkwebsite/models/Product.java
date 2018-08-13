package com.techmark.techmarkwebsite.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.techmark.techmarkwebsite.serializers.ProductSerializer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@JsonSerialize(using = ProductSerializer.class)
public class Product implements Comparable<Product> {
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
    private String imageUrl;
    
    @ManyToOne
    @JoinColumn(name = "CategoryID")
		@JsonManagedReference
    private Category category;
	
		@OneToMany(mappedBy = "product")
    @JsonBackReference
		private List<OrderDetail> orderDetails;
	
    public Product() {
    }

    public Product(int productId, String name, int price, String description, String imageUrl, Category category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }
    
    public Product(String name, int price, String description, String imageUrl, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
	
	  public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	
	  public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
    
    @Override
    public String toString() {
        return "Product: " +
            "id = " + productId +
            ", name = " + name +
            ", price = " + price +
            ", description = " + description +
            ", category" + category;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Product) {
            Product product = (Product) object;
            return this.getName().equals(product.getName());
        }
        
        return false;
    }
    
    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
    }
}
