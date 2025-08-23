package com.example.qlnhahangculcat.database.service;

import com.example.qlnhahangculcat.model.Food;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface FoodService {
    /**
     * Lấy tất cả các món ăn.
     * Endpoint: GET food.php
     */
    @GET("food.php")
    Call<List<Food>> getAllFoods();

    /**
     * Lấy tất cả danh mục món ăn.
     * Endpoint: GET food.php?action=categories
     */
    @GET("food.php")
    Call<List<String>> getAllFoodCategories(@Query("action") String action);

    /**
     * Lấy một món ăn theo ID.
     * Endpoint: GET food.php?id={id}
     */
    @GET("food.php")
    Call<Food> getFoodById(@Query("id") int id);

    /**
     * Lấy danh sách món ăn theo danh mục.
     * Endpoint: GET food.php?category={category}
     */
    @GET("food.php")
    Call<List<Food>> getFoodsByCategory(@Query("category") String category);

    /**
     * Lấy danh sách món ăn được sắp xếp.
     * Endpoint: GET food.php?action=sorted&sort_by={sortBy}&order={order}
     *
     * @param sortBy Tên cột để sắp xếp (ví dụ: "name", "price").
     * @param order  Kiểu sắp xếp ("asc" hoặc "desc").
     */
    @GET("food.php")
    Call<List<Food>> getFoodsSorted(
            @Query("action") String action,
            @Query("sort_by") String sortBy,
            @Query("order") String order
    );

    /**
     * Lấy danh sách món ăn theo danh mục và được sắp xếp.
     * Endpoint: GET food.php?action=category&category={category}&sort_by={sortBy}&order={order}
     *
     * @param category Tên danh mục.
     * @param sortBy   Tên cột để sắp xếp.
     * @param order    Kiểu sắp xếp ("asc" hoặc "desc").
     */
    @GET("food.php")
    Call<List<Food>> getFoodsByCategorySorted(
            @Query("action") String action,
            @Query("category") String category,
            @Query("sort_by") String sortBy,
            @Query("order") String order
    );

    /**
     * Tìm kiếm món ăn theo từ khóa.
     * Endpoint: GET food.php?action=search&query={query}
     *
     * @param searchQuery Từ khóa tìm kiếm.
     */
    @GET("food.php")
    Call<List<Food>> searchFoods(
            @Query("action") String action,
            @Query("query") String searchQuery
    );

    /**
     * Thêm một món ăn mới.
     * Endpoint: POST food.php (body JSON)
     */
    @POST("food.php")
    Call<JsonObject> addFood(@Body Food food);

    /**
     * Cập nhật thông tin món ăn.
     * Endpoint: PUT food.php?id={id} (body JSON)
     *
     * @param id          ID của món ăn cần cập nhật.
     * @param foodUpdates JsonObject chứa các trường cần cập nhật.
     */
    @PUT("food.php")
    Call<JsonObject> updateFood(@Query("id") int id, @Body JsonObject foodUpdates);

    /**
     * Xóa một món ăn theo ID.
     * Endpoint: DELETE food.php?id={id}
     */
    @DELETE("food.php")
    Call<JsonObject> deleteFood(@Query("id") int id);
}
