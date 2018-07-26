package com.techmark.techmarkwebsite.models;

public class Product {
    private int productID;
    private String name;
    private int price;
    private String description;
    private String imageURL;
    private int categoryID;

    public Product() {
    }

    public Product(int productID, String name, int price, String description, String imageURL, int categoryID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
        this.categoryID = categoryID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
