package com.example.qlnhahangculcat.database.service;

import com.example.qlnhahangculcat.model.DailyMenuItem;
import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface DailyMenuService {
    @GET("daily_menu.php")
    Call<List<String>> getAvailableDailyMenuDates(@Query("action") String action);

    @GET("daily_menu.php")
    Call<List<DailyMenuItem>> getDailyMenuByDate(@Query("action") String action, @Query("date") String date);

    @GET("daily_menu.php")
    Call<DailyMenuItem> getDailyMenuItemById(@Query("id") int id);

    @POST("daily_menu.php")
    Call<JsonObject> addDailyMenuItem(@Body DailyMenuItem item);

    @PUT("daily_menu.php")
    Call<JsonObject> updateDailyMenuItem(@Query("id") int id, @Body JsonObject itemUpdates);

    @DELETE("daily_menu.php")
    Call<JsonObject> deleteDailyMenuItem(@Query("id") int id);

    @DELETE("daily_menu.php")
    Call<JsonObject> deleteAllDailyMenuItemsByDate(@Query("date") String date);
}
