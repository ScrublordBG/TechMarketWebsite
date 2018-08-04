package com.techmark.techmarkwebsite.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int categoryID;

    @Column(name = "Name")
    private String name;

    @OneToMany(
    		//cascade = {CascadeType.DETACH, CascadeType.MERGE},
    		mappedBy = "category",
				fetch = FetchType.EAGER) /*keep fetch type EAGER if you want getAllProductsByCategory() to work*/
    @JsonBackReference
    private List<Product> products;

    public Category(){

    }

    public Category(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }
	
	public Category(String name) {
    	this.name = name;
	}
	
	public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
