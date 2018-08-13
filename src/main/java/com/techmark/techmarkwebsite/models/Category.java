package com.techmark.techmarkwebsite.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category implements Comparable<Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int categoryId;

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

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
	
	public Category(String name) {
    	this.name = name;
	}
	
	public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
    
    @Override
    public String toString() {
        return "Category: " +
            ", id = " + categoryId +
            ", name = " + name;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Category) {
            Category category = (Category) object;
            return this.getCategoryId() == category.getCategoryId();
        }
        
        return false;
    }
    
    @Override
    public int compareTo(Category o) {
        Integer thisId = this.getCategoryId();
        Integer thatId = o.getCategoryId();
        return thisId.compareTo(thatId);
    }
}
