package com.example.qlnhahangculcat.database.service;

import com.example.qlnhahangculcat.model.OrdersByStatus;
import com.example.qlnhahangculcat.model.TopSellingFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StatisticsService {
    @GET("statistics_api.php")
    Call<Double> getDailyRevenue(@Query("action") String action, @Query("date") String date);

    @GET("statistics_api.php")
    Call<Double> getMonthlyRevenue(@Query("action") String action, @Query("year") int year, @Query("month") int month);

    @GET("statistics_api.php")
    Call<Double> getTotalRevenue(@Query("action") String action, @Query("start_date") String startDate, @Query("end_date") String endDate);

    @GET("statistics_api.php")
    Call<List<TopSellingFood>> getTopSellingFoods(@Query("action") String action, @Query("start_date") String startDate, @Query("end_date") String endDate, @Query("limit") int limit);

    @GET("statistics_api.php")
    Call<List<OrdersByStatus>> getOrdersByStatus(@Query("action") String action, @Query("start_date") String startDate, @Query("end_date") String endDate);
}
