package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    @SerializedName("id")
    private int id;

    @SerializedName("table_id")
    private int tableId;

    @SerializedName("table_name")
    private String tableName;

    @SerializedName("order_date")
    private long orderDate; // Thời gian Unix timestamp (millis)

    @SerializedName("order_date_formatted")
    private String orderDateFormatted; // Chuỗi ngày đã được định dạng từ API

    @SerializedName("status")
    private String status;

    @SerializedName("total_amount")
    private double totalAmount;

    @SerializedName("order_items")
    private List<OrderItem> orderItems; // Danh sách các món trong đơn hàng

    public Order() {
    }

    // Constructor cho việc thêm mới
    public Order(int tableId, long orderDate, String status, double totalAmount, List<OrderItem> orderItems) {
        this.tableId = tableId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orderItems = orderItems;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDateFormatted() {
        return orderDateFormatted;
    }

    public void setOrderDateFormatted(String orderDateFormatted) {
        this.orderDateFormatted = orderDateFormatted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}