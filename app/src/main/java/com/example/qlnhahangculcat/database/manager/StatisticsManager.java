package com.example.qlnhahangculcat.database.manager;

import com.example.qlnhahangculcat.database.RetrofitClient;
import com.example.qlnhahangculcat.model.OrdersByStatus;
import com.example.qlnhahangculcat.model.TopSellingFood;
import com.example.qlnhahangculcat.database.service.StatisticsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsManager {
    private StatisticsService statisticsService;

    public StatisticsManager() {
        statisticsService = RetrofitClient.getRetrofitInstance().create(StatisticsService.class);
    }

    // --- Phương thức API ---

    public void getDailyRevenue(String date, final StatisticsCallback<Double> callback) {
        Call<Double> call = statisticsService.getDailyRevenue("daily_revenue", date);
        call.enqueue(new StatisticsResponseCallback<>(callback));
    }

    public void getMonthlyRevenue(int year, int month, final StatisticsCallback<Double> callback) {
        Call<Double> call = statisticsService.getMonthlyRevenue("monthly_revenue", year, month);
        call.enqueue(new StatisticsResponseCallback<>(callback));
    }

    public void getTotalRevenue(String startDate, String endDate, final StatisticsCallback<Double> callback) {
        Call<Double> call = statisticsService.getTotalRevenue("total_revenue", startDate, endDate);
        call.enqueue(new StatisticsResponseCallback<>(callback));
    }

    public void getTopSellingFoods(String startDate, String endDate, int limit, final StatisticsCallback<List<TopSellingFood>> callback) {
        Call<List<TopSellingFood>> call = statisticsService.getTopSellingFoods("top_selling_foods", startDate, endDate, limit);
        call.enqueue(new StatisticsResponseCallback<>(callback));
    }

    public void getOrdersByStatus(String startDate, String endDate, final StatisticsCallback<List<OrdersByStatus>> callback) {
        Call<List<OrdersByStatus>> call = statisticsService.getOrdersByStatus("orders_by_status", startDate, endDate);
        call.enqueue(new StatisticsResponseCallback<>(callback));
    }

    // --- Callback Interface ---

    public interface StatisticsCallback<T> {
        void onSuccess(T result);

        void onError(String error);
    }

    // --- Private Callback Helper Class ---

    private static class StatisticsResponseCallback<T> implements Callback<T> {
        private final StatisticsCallback<T> callback;

        public StatisticsResponseCallback(StatisticsCallback<T> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                callback.onSuccess(response.body());
            } else {
                callback.onError("API Error: " + response.message() + " (" + response.code() + ")");
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            callback.onError("Network Error: " + t.getMessage());
        }
    }
}
