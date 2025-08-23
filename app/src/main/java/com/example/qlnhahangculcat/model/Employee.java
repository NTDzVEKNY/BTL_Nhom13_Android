package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;
import com.example.qlnhahangculcat.model.Position;

public class Employee {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    // Sử dụng enum thay cho String
    @SerializedName("position")
    private Position position;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("address")
    private String address;

    @SerializedName("salary")
    private double salary;

    @SerializedName("start_date")
    private String startDate;

    // Constructors
    public Employee(String name, Position position, String phone, String email, String address, double salary, String startDate) {
        this.name = name;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.startDate = startDate;
    }

    public Employee(int id, String name, Position position, String phone, String email, String address, double salary, String startDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.startDate = startDate;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}