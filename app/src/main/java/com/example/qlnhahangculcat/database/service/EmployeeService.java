package com.example.qlnhahangculcat.database.service;

import com.example.qlnhahangculcat.model.Employee;
import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
public interface EmployeeService {
    /**
     * Lấy tất cả nhân viên.
     * Endpoint: GET employee.php
     */
    @GET("employee.php")
    Call<List<Employee>> getAllEmployees();

    /**
     * Lấy một nhân viên theo ID.
     * Endpoint: GET employee.php?id={id}
     */
    @GET("employee.php")
    Call<Employee> getEmployee(@Query("id") int id);

    /**
     * Thêm một nhân viên mới.
     * Endpoint: POST employee.php (body JSON)
     */
    @POST("employee.php")
    Call<JsonObject> addEmployee(@Body Employee employee);

    /**
     * Cập nhật thông tin nhân viên.
     * Endpoint: PUT employee.php?id={id} (body JSON)
     * @param id ID của nhân viên cần cập nhật.
     * @param employeeUpdates JsonObject chứa các trường cần cập nhật.
     */
    @PUT("employee.php")
    Call<JsonObject> updateEmployee(@Query("id") int id, @Body JsonObject employeeUpdates);

    /**
     * Xóa một nhân viên theo ID.
     * Endpoint: DELETE employee.php?id={id}
     */
    @DELETE("employee.php")
    Call<JsonObject> deleteEmployee(@Query("id") int id);

    /**
     * Kiểm tra sự tồn tại của số điện thoại.
     * Endpoint: GET employee.php?action=check_phone&phone={phone}&current_employee_id={id}
     * @param action Hành động "check_phone".
     * @param phone Số điện thoại cần kiểm tra.
     * @param currentEmployeeId ID của nhân viên hiện tại (để loại trừ khỏi việc kiểm tra).
     */
    @GET("employee.php")
    Call<JsonObject> checkIfPhoneNumberExists(
            @Query("action") String action,
            @Query("phone") String phone,
            @Query("current_employee_id") int currentEmployeeId
    );
}
