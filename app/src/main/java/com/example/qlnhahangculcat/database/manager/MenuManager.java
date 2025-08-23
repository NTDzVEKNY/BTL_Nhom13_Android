package com.example.qlnhahangculcat.database.manager;

import com.example.qlnhahangculcat.database.RetrofitClient;
import com.example.qlnhahangculcat.model.Food;
import com.example.qlnhahangculcat.database.service.MenuService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuManager {
    private MenuService menuService;

    public MenuManager() {
        menuService = RetrofitClient.getRetrofitInstance().create(MenuService.class);
    }

    // --- Phương thức API ---

    public void getMenuItemsForToday(final MenuCallback<List<Food>> callback) {
        Call<List<Food>> call = menuService.getMenuItemsForToday("get_menu_today");
        call.enqueue(new MenuResponseCallback<>(callback));
    }

    public void getMenuItemsForDate(String date, final MenuCallback<List<Food>> callback) {
        Call<List<Food>> call = menuService.getMenuItemsForDate("get_menu_by_date", date);
        call.enqueue(new MenuResponseCallback<>(callback));
    }

    public void getAllAvailableFoods(final MenuCallback<List<Food>> callback) {
        Call<List<Food>> call = menuService.getAllAvailableFoods("get_available_foods");
        call.enqueue(new MenuResponseCallback<>(callback));
    }

    // --- Callback Interface ---

    public interface MenuCallback<T> {
        void onSuccess(T result);
        void onError(String error);
    }

    // --- Private Callback Helper Class ---

    private static class MenuResponseCallback<T> implements Callback<T> {
        private final MenuCallback<T> callback;

        public MenuResponseCallback(MenuCallback<T> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful() && response.body() != null) {
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
