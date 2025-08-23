package com.example.qlnhahangculcat.database.manager;

import com.example.qlnhahangculcat.database.RetrofitClient;
import com.example.qlnhahangculcat.model.DailyMenuItem;
import com.example.qlnhahangculcat.database.service.DailyMenuService;
import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class DailyMenuManager {
    private DailyMenuService dailyMenuService;

    public DailyMenuManager() {
        dailyMenuService = RetrofitClient.getRetrofitInstance().create(DailyMenuService.class);
    }

    public void getAvailableDailyMenuDates(final GetDatesCallback callback) {
        Call<List<String>> call = dailyMenuService.getAvailableDailyMenuDates("dates");
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get dates: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void getDailyMenuByDate(String date, final GetMenuCallback callback) {
        Call<List<DailyMenuItem>> call = dailyMenuService.getDailyMenuByDate("by_date", date);
        call.enqueue(new Callback<List<DailyMenuItem>>() {
            @Override
            public void onResponse(Call<List<DailyMenuItem>> call, Response<List<DailyMenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get menu for date: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<DailyMenuItem>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void addDailyMenuItem(DailyMenuItem item, final AddItemCallback callback) {
        Call<JsonObject> call = dailyMenuService.addDailyMenuItem(item);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().get("message").getAsString());
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        JsonObject errorJson = new com.google.gson.Gson().fromJson(errorBody, JsonObject.class);
                        callback.onError(errorJson.get("message").getAsString());
                    } catch (Exception e) {
                        callback.onError("Error: " + response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void updateDailyMenuItem(int id, JsonObject updates, final UpdateItemCallback callback) {
        Call<JsonObject> call = dailyMenuService.updateDailyMenuItem(id, updates);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().get("message").getAsString());
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        JsonObject errorJson = new com.google.gson.Gson().fromJson(errorBody, JsonObject.class);
                        callback.onError(errorJson.get("message").getAsString());
                    } catch (Exception e) {
                        callback.onError("Error: " + response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    public void deleteDailyMenuItem(int id, final DeleteItemCallback callback) {
        Call<JsonObject> call = dailyMenuService.deleteDailyMenuItem(id);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().get("message").getAsString());
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        JsonObject errorJson = new com.google.gson.Gson().fromJson(errorBody, JsonObject.class);
                        callback.onError(errorJson.get("message").getAsString());
                    } catch (Exception e) {
                        callback.onError("Error: " + response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    // Define Callback Interfaces
    public interface GetDatesCallback {
        void onSuccess(List<String> dates);
        void onError(String error);
    }
    public interface GetMenuCallback {
        void onSuccess(List<DailyMenuItem> menuItems);
        void onError(String error);
    }
    public interface AddItemCallback {
        void onSuccess(String message);
        void onError(String error);
    }
    public interface UpdateItemCallback {
        void onSuccess(String message);
        void onError(String error);
    }
    public interface DeleteItemCallback {
        void onSuccess(String message);
        void onError(String error);
    }
}
