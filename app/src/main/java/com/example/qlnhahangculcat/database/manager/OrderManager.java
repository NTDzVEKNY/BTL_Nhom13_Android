package com.example.qlnhahangculcat.database.manager;

import com.example.qlnhahangculcat.database.RetrofitClient;
import com.example.qlnhahangculcat.model.Order;
import com.example.qlnhahangculcat.model.OrderItem;
import com.example.qlnhahangculcat.database.service.OrderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderManager {
    private OrderService orderService;

    public OrderManager() {
        orderService = RetrofitClient.getRetrofitInstance().create(OrderService.class);
    }

    // --- Phương thức API ---

    public void getCurrentOrderForTable(int tableId, final OrderCallback<Order> callback) {
        Call<Order> call = orderService.getCurrentOrderForTable("current_for_table", tableId);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void getOrderHistoryForTable(int tableId, final OrderCallback<List<Order>> callback) {
        Call<List<Order>> call = orderService.getOrderHistoryForTable("history_for_table", tableId);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void getOrdersByDate(String date, final OrderCallback<List<Order>> callback) {
        Call<List<Order>> call = orderService.getOrdersByDate("by_date", date);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void getOrderById(int orderId, final OrderCallback<Order> callback) {
        Call<Order> call = orderService.getOrderById(orderId);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void addOrder(Order order, final OrderCallback<Void> callback) {
        Call<Void> call = orderService.addOrder(order);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void saveOrder(Order order, final OrderCallback<Void> callback) {
        Call<Void> call = orderService.saveOrder(order);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void addOrderItem(int orderId, OrderItem item, final OrderCallback<Void> callback) {
        Call<Void> call = orderService.addOrderItem("add_order_item", orderId, item);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void updateOrderStatus(int orderId, String status, final OrderCallback<Void> callback) {
        Order order = new Order();
        order.setStatus(status);
        Call<Void> call = orderService.updateOrderStatus("update_status", orderId, order);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void updateOrderItem(int orderItemId, OrderItem item, final OrderCallback<Void> callback) {
        Call<Void> call = orderService.updateOrderItem("update_order_item", orderItemId, item);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    public void updateOrder(int orderId, Order order, final OrderCallback<Void> callback) {
        Call<Void> call = orderService.updateOrder("update_order", orderId, order);
        call.enqueue(new OrderResponseCallback<>(callback));
    }

    // --- Callback Interface ---

    public interface OrderCallback<T> {
        void onSuccess(T result);

        void onError(String error);
    }

    // --- Private Callback Helper Class ---

    private static class OrderResponseCallback<T> implements Callback<T> {
        private final OrderCallback<T> callback;

        public OrderResponseCallback(OrderCallback<T> callback) {
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
