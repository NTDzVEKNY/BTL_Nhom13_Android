package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;

public class TopSellingFood {

    @SerializedName("food_id")
    private int foodId;

    @SerializedName("food_name")
    private String foodName;

    @SerializedName("total_sold_quantity")
    private int totalSoldQuantity;

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

    public int getTotalSoldQuantity() {
        return totalSoldQuantity;
    }

    public void setTotalSoldQuantity(int totalSoldQuantity) {
        this.totalSoldQuantity = totalSoldQuantity;
    }
}