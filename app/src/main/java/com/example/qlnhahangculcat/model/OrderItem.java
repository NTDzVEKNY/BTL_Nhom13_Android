package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;

public class OrderItem {

    @SerializedName("id")
    private int id;

    @SerializedName("order_id")
    private int orderId;

    @SerializedName("food_id")
    private int foodId;

    @SerializedName("food_name")
    private String foodName; // Tên món ăn từ API (sử dụng COALESCE)

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("price")
    private double price;

    public OrderItem() {
    }

    // Constructor cho việc thêm mới
    public OrderItem(int orderId, int foodId, String foodName, int quantity, double price) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}