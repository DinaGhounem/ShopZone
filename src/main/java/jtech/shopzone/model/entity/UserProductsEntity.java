/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.entity;

import java.util.Date;

/**
 *
 * @author Dina PC
 */
public class UserProductsEntity {

    private int userId;
    private int productId;
    private int quantity;
    private Date date;
    private double price;

    public UserProductsEntity() {
    }

    public UserProductsEntity(int userId, int productId, int quantity,Date date,double price) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.date=date;
        this.price=price;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UserProductsEntity{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
