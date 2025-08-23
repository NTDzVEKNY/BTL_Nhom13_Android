package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;
import com.example.qlnhahangculcat.model.FoodCategory;

public class Food {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    // Sử dụng enum thay cho String
    @SerializedName("category")
    private FoodCategory category;

    @SerializedName("price")
    private double price;

    @SerializedName("description")
    private String description;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("available")
    private int available;

    public Food() {
    }

    // Constructor cho việc thêm mới
    public Food(String name, FoodCategory category, double price, String description, String imageUrl, int available) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.available = available;
    }

    // Constructor cho việc nhận dữ liệu từ API
    public Food(int id, String name, FoodCategory category, double price, String description, String imageUrl, int available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.available = available;
    }

    // Getters and Setters
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

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public boolean isAvailable() {
        return available == 1;
    }

    public void setAvailable(boolean available) {
        this.available = available ? 1 : 0;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}