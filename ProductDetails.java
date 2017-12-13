/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.model;

import java.sql.Date;

/**
 *
 * @author iamsu
 */
public class ProductDetails {
    private long serialNumber;
    private int productId;
    private Date manufactureDate;
    private Date expieryDate;
    private String size;
    private String color;
    private int sellerId;

    public ProductDetails(long serialNumber, int productId, Date manufactureDate, Date expieryDate, String size, String color, int sellerId) {
        this.serialNumber = serialNumber;
        this.productId = productId;
        this.manufactureDate = manufactureDate;
        this.expieryDate = expieryDate;
        this.size = size;
        this.color = color;
        this.sellerId = sellerId;
    }

    public ProductDetails(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Date getExpieryDate() {
        return expieryDate;
    }

    public void setExpieryDate(Date expieryDate) {
        this.expieryDate = expieryDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
    
    
}


