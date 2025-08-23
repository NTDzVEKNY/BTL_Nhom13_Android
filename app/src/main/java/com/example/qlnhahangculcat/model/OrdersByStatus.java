package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;

public class OrdersByStatus {

    @SerializedName("status")
    private String status;

    @SerializedName("count")
    private int count;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}