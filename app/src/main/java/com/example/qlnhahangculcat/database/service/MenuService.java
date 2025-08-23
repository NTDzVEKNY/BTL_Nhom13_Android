package com.example.qlnhahangculcat.database.service;

import com.example.qlnhahangculcat.model.Food;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface MenuService {
    /**
     * Lấy danh sách các món ăn có sẵn trong ngày hôm nay.
     * Endpoint: GET menu.php?action=get_menu_today
     */
    @GET("menu.php")
    Call<List<Food>> getMenuItemsForToday(@Query("action") String action);

    /**
     * Lấy danh sách các món ăn có sẵn cho một ngày cụ thể.
     * Endpoint: GET menu.php?action=get_menu_by_date&date={date}
     *
     * @param date Ngày cần lấy thực đơn, định dạng 'YYYY-MM-DD'.
     */
    @GET("menu.php")
    Call<List<Food>> getMenuItemsForDate(@Query("action") String action, @Query("date") String date);

    /**
     * Lấy tất cả các món ăn đang có trạng thái 'available' (sẵn có).
     * Endpoint: GET menu.php?action=get_available_foods
     */
    @GET("menu.php")
    Call<List<Food>> getAllAvailableFoods(@Query("action") String action);
}
