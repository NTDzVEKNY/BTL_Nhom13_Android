package com.example.qlnhahangculcat.database.manager;

import com.example.qlnhahangculcat.database.RetrofitClient;
import com.example.qlnhahangculcat.model.Employee;
import com.example.qlnhahangculcat.database.service.EmployeeService;
import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class EmployeeManager {
    private EmployeeService employeeService;

    public EmployeeManager() {
        employeeService = RetrofitClient.getRetrofitInstance().create(EmployeeService.class);
    }

    /**
     * Lấy tất cả nhân viên.
     * @param callback Callback để xử lý danh sách nhân viên.
     */
    public void getAllEmployees(final GetAllEmployeesCallback callback) {
        Call<List<Employee>> call = employeeService.getAllEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to retrieve employees: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    /**
     * Lấy thông tin một nhân viên theo ID.
     * @param id ID của nhân viên.
     * @param callback Callback để xử lý thông tin nhân viên.
     */
    public void getEmployee(int id, final GetEmployeeCallback callback) {
        Call<Employee> call = employeeService.getEmployee(id);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to retrieve employee: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    /**
     * Thêm một nhân viên mới.
     * @param employee Đối tượng Employee cần thêm.
     * @param callback Callback để xử lý kết quả.
     */
    public void addEmployee(Employee employee, final AddUpdateDeleteEmployeeCallback callback) {
        Call<JsonObject> call = employeeService.addEmployee(employee);
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

    /**
     * Cập nhật thông tin nhân viên.
     * @param id ID của nhân viên cần cập nhật.
     * @param employeeUpdates JsonObject chứa các trường cần cập nhật (chỉ gửi những trường thay đổi).
     * @param callback Callback để xử lý kết quả.
     */
    public void updateEmployee(int id, JsonObject employeeUpdates, final AddUpdateDeleteEmployeeCallback callback) {
        Call<JsonObject> call = employeeService.updateEmployee(id, employeeUpdates);
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

    /**
     * Xóa một nhân viên.
     * @param id ID của nhân viên cần xóa.
     * @param callback Callback để xử lý kết quả.
     */
    public void deleteEmployee(int id, final AddUpdateDeleteEmployeeCallback callback) {
        Call<JsonObject> call = employeeService.deleteEmployee(id);
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

    /**
     * Kiểm tra sự tồn tại của số điện thoại.
     * @param phone Số điện thoại cần kiểm tra.
     * @param currentEmployeeId ID của nhân viên hiện tại (để loại trừ khỏi việc kiểm tra trùng lặp).
     * @param callback Callback để xử lý kết quả.
     */
    public void checkIfPhoneNumberExists(String phone, int currentEmployeeId, final CheckPhoneCallback callback) {
        Call<JsonObject> call = employeeService.checkIfPhoneNumberExists("check_phone", phone, currentEmployeeId);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonResponse = response.body();
                    boolean exists = jsonResponse.get("phone_number_exists").getAsBoolean();
                    callback.onResult(exists);
                } else {
                    callback.onError("Failed to check phone number: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    // --- Callback Interfaces ---
    public interface GetAllEmployeesCallback {
        void onSuccess(List<Employee> employees);
        void onError(String error);
    }

    public interface GetEmployeeCallback {
        void onSuccess(Employee employee);
        void onError(String error);
    }

    public interface AddUpdateDeleteEmployeeCallback {
        void onSuccess(String message);
        void onError(String error);
    }

    public interface CheckPhoneCallback {
        void onResult(boolean exists);
        void onError(String error);
    }
}
