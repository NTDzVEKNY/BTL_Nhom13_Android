package com.example.qlnhahangculcat.database.manager;

import com.example.qlnhahangculcat.database.RetrofitClient;
import com.example.qlnhahangculcat.model.Food;
import com.example.qlnhahangculcat.database.service.FoodService;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodManager {
    private FoodService foodService;

    public FoodManager() {
        foodService = RetrofitClient.getRetrofitInstance().create(FoodService.class);
    }

    // --- Phương thức API ---

    public void getAllFoods(final FoodCallback<List<Food>> callback) {
        Call<List<Food>> call = foodService.getAllFoods();
        call.enqueue(new FoodResponseCallback<>(callback));
    }

    public void getAllFoodCategories(final FoodCallback<List<String>> callback) {
        Call<List<String>> call = foodService.getAllFoodCategories("categories");
        call.enqueue(new FoodResponseCallback<>(callback));
    }

    public void getFoodById(int id, final FoodCallback<Food> callback) {
        Call<Food> call = foodService.getFoodById(id);
        call.enqueue(new FoodResponseCallback<>(callback));
    }

    public void getFoodsByCategory(String category, final FoodCallback<List<Food>> callback) {
        Call<List<Food>> call = foodService.getFoodsByCategory(category);
        call.enqueue(new FoodResponseCallback<>(callback));
    }

    public void getFoodsSorted(String sortBy, String order, final FoodCallback<List<Food>> callback) {
        Call<List<Food>> call = foodService.getFoodsSorted("sorted", sortBy, order);
        call.enqueue(new FoodResponseCallback<>(callback));
    }

    public void getFoodsByCategorySorted(String category, String sortBy, String order, final FoodCallback<List<Food>> callback) {
        Call<List<Food>> call = foodService.getFoodsByCategorySorted("category", category, sortBy, order);
        call.enqueue(new FoodResponseCallback<>(callback));
    }

    public void searchFoods(String searchQuery, final FoodCallback<List<Food>> callback) {
        Call<List<Food>> call = foodService.searchFoods("search", searchQuery);
        call.enqueue(new FoodResponseCallback<>(callback));
    }

    public void addFood(Food food, final FoodCallback<String> callback) {
        Call<JsonObject> call = foodService.addFood(food);
        call.enqueue(new MessageResponseCallback(callback));
    }

    public void updateFood(int id, JsonObject foodUpdates, final FoodCallback<String> callback) {
        Call<JsonObject> call = foodService.updateFood(id, foodUpdates);
        call.enqueue(new MessageResponseCallback(callback));
    }

    public void deleteFood(int id, final FoodCallback<String> callback) {
        Call<JsonObject> call = foodService.deleteFood(id);
        call.enqueue(new MessageResponseCallback(callback));
    }

    // --- Callback Interfaces ---

    public interface FoodCallback<T> {
        void onSuccess(T result);

        void onError(String error);
    }

    // --- Private Callback Helper Classes ---

    private static class FoodResponseCallback<T> implements Callback<T> {
        private final FoodCallback<T> callback;

        public FoodResponseCallback(FoodCallback<T> callback) {
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

    private static class MessageResponseCallback implements Callback<JsonObject> {
        private final FoodCallback<String> callback;

        public MessageResponseCallback(FoodCallback<String> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful() && response.body() != null) {
                String message = response.body().get("message").getAsString();
                callback.onSuccess(message);
            } else {
                try {
                    String errorBody = response.errorBody() != null ? response.errorBody().string() : "Unknown Error";
                    JsonObject errorJson = new com.google.gson.Gson().fromJson(errorBody, JsonObject.class);
                    if (errorJson != null && errorJson.has("message")) {
                        callback.onError(errorJson.get("message").getAsString());
                    } else {
                        callback.onError("API Error: " + response.message() + " (" + response.code() + ") - " + errorBody);
                    }
                } catch (Exception e) {
                    callback.onError("Error parsing error response: " + e.getMessage());
                }
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            callback.onError("Network Error: " + t.getMessage());
        }
    }
}
