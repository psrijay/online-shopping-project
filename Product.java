/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.model;



public class Product{
    private int id;
    private String name;
    private String description;
    private float price;
    private int categoryId;
    private String manufacturename;
    private String image;

    public Product() {
    }

    public Product(String name, String description, float price, int categoryId, String manufacturename) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.manufacturename = manufacturename;
    }

    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getManufacturename() {
        return manufacturename;
    }

    public void setManufacturename(String manufacturename) {
        this.manufacturename = manufacturename;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
}
