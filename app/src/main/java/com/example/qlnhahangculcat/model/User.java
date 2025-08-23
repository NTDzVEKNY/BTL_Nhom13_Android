package com.example.qlnhahangculcat.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("username")
    private String username;

    // Thuộc tính này chỉ dùng để gửi lên, không nhận về từ server
    @SerializedName("password")
    private String password;

    // Constructor để tạo đối tượng khi gửi lên server
    public User(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    // Các phương thức getter và setter
    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }
}
