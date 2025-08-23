package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;

public class DailyMenuItem {
    @SerializedName("id")
    private int id;

    @SerializedName("date")
    private String date;

    @SerializedName("food_id")
    private int foodId;

    @SerializedName("featured")
    private int featured; // 0 for false, 1 for true

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("food_name")
    private String foodName;

    @SerializedName("food_category")
    private String foodCategory;

    @SerializedName("food_price")
    private double foodPrice;

    @SerializedName("food_image_url")
    private String foodImageUrl;

    // Constructors
    public DailyMenuItem(String date, int foodId, int featured, int quantity) {
        this.date = date;
        this.foodId = foodId;
        this.featured = featured;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getFeatured() {
        return featured;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public String getFoodImageUrl() {
        return foodImageUrl;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}