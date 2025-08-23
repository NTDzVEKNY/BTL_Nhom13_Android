package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("status")
    private String status; // "available" or "occupied"

    @SerializedName("capacity")
    private int capacity;

    public Table() {
    }

    public Table(String name, String status, int capacity) {
        this.name = name;
        this.status = status;
        this.capacity = capacity;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}