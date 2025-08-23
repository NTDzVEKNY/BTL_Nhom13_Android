package com.example.qlnhahangculcat.database.service;

import com.example.qlnhahangculcat.model.Order;
import com.example.qlnhahangculcat.model.OrderItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface OrderService {
    @GET("order_api.php")
    Call<Order> getCurrentOrderForTable(@Query("action") String action, @Query("table_id") int tableId);

    @GET("order_api.php")
    Call<List<OrderItem>> getOrderItemsForOrder(@Query("action") String action, @Query("order_id") int orderId);

    @GET("order_api.php")
    Call<List<Order>> getOrderHistoryForTable(@Query("action") String action, @Query("table_id") int tableId);

    @GET("order_api.php")
    Call<List<Order>> getOrdersByDate(@Query("action") String action, @Query("date") String date);

    @GET("order_api.php")
    Call<Order> getOrderById(@Query("id") int orderId);

    @POST("order_api.php")
    Call<Void> addOrder(@Body Order order);

    @POST("order_api.php?action=save_order")
    Call<Void> saveOrder(@Body Order order);

    @POST("order_api.php")
    Call<Void> addOrderItem(@Query("action") String action, @Query("order_id") int orderId, @Body OrderItem item);

    @PUT("order_api.php")
    Call<Void> updateOrderStatus(@Query("action") String action, @Query("id") int orderId, @Body Order order);

    @PUT("order_api.php")
    Call<Void> updateOrderItem(@Query("action") String action, @Query("id") int orderItemId, @Body OrderItem item);

    @PUT("order_api.php")
    Call<Void> updateOrder(@Query("action") String action, @Query("id") int orderId, @Body Order order);
}
