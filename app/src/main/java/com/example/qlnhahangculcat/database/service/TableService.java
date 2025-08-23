package com.example.qlnhahangculcat.database.service;

import com.example.qlnhahangculcat.model.Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface TableService {
    /**
     * Lấy tất cả các bàn ăn.
     * Endpoint: GET table_api.php?action=all
     */
    @GET("table_api.php")
    Call<List<Table>> getAllTables(@Query("action") String action);

    /**
     * Lấy một bàn ăn theo ID.
     * Endpoint: GET table_api.php?id={id}
     */
    @GET("table_api.php")
    Call<Table> getTableById(@Query("id") int id);

    /**
     * Lấy các bàn ăn theo trạng thái.
     * Endpoint: GET table_api.php?action=by_status&status={status}
     */
    @GET("table_api.php")
    Call<List<Table>> getTablesByStatus(@Query("action") String action, @Query("status") String status);

    /**
     * Thêm một bàn ăn mới.
     * Endpoint: POST table_api.php
     */
    @POST("table_api.php")
    Call<Void> addTable(@Query("action") String action, @Body Table table);

    /**
     * Cập nhật thông tin bàn ăn.
     * Endpoint: PUT table_api.php?action=update_table&id={id}
     */
    @PUT("table_api.php")
    Call<Void> updateTable(@Query("action") String action, @Query("id") int id, @Body Table table);

    /**
     * Cập nhật trạng thái bàn ăn.
     * Endpoint: PUT table_api.php?action=update_status&id={id}
     */
    @PUT("table_api.php")
    Call<Void> updateTableStatus(@Query("action") String action, @Query("id") int id, @Body Table table);

    /**
     * Xóa một bàn ăn.
     * Endpoint: DELETE table_api.php?id={id}
     */
    @DELETE("table_api.php")
    Call<Void> deleteTable(@Query("id") int id);
}
