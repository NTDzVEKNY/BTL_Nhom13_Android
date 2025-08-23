package com.example.qlnhahangculcat.database.manager;

import com.example.qlnhahangculcat.database.RetrofitClient;
import com.example.qlnhahangculcat.database.service.UserService;
import com.example.qlnhahangculcat.model.User;
import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UserManager {
    private UserService userService;

    public UserManager() {
        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);
    }

    /**
     * Lấy danh sách tất cả người dùng từ API.
     * @param callback Callback để xử lý danh sách người dùng.
     */
    public void getUsers(final GetUsersCallback callback) {
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to retrieve users: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    /**
     * Kiểm tra sự tồn tại của tên đăng nhập.
     * @param username Tên đăng nhập cần kiểm tra.
     * @param callback Callback để xử lý kết quả.
     */
    public void checkIfUserExists(String username, final CheckUserCallback callback) {
        Call<JsonObject> call = userService.checkIfUserExists(username);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonResponse = response.body();
                    boolean exists = jsonResponse.get("username_exists").getAsBoolean();
                    callback.onResult(exists);
                } else {
                    callback.onError("Failed to check user existence: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    /**
     * Phương thức để thêm người dùng mới qua API.
     * @param user Đối tượng User cần thêm.
     * @param callback Callback để xử lý kết quả.
     */
    public void addUser(User user, final AddUserCallback callback) {
        Call<JsonObject> call = userService.addUser(user);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonResponse = response.body();
                    if (jsonResponse.has("message") && jsonResponse.get("message").getAsString().contains("success")) {
                        int newUserId = jsonResponse.has("id") ? jsonResponse.get("id").getAsInt() : -1;
                        callback.onSuccess("Thêm người dùng thành công! ID: " + newUserId);
                    } else {
                        callback.onError(jsonResponse.get("message").getAsString());
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        JsonObject errorJson = new com.google.gson.Gson().fromJson(errorBody, JsonObject.class);
                        if (errorJson.has("message")) {
                            callback.onError(errorJson.get("message").getAsString());
                        } else {
                            callback.onError("Lỗi không xác định: " + response.code());
                        }
                    } catch (Exception e) {
                        callback.onError("Lỗi phản hồi: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError("Lỗi kết nối mạng: " + t.getMessage());
            }
        });
    }

    /**
     * Phương thức để đăng nhập người dùng qua API.
     * @param username Tên đăng nhập.
     * @param password Mật khẩu.
     * @param callback Callback để xử lý kết quả.
     */
    public void loginUser(String username, String password, final LoginUserCallback callback) {
        User user = new User(null, username, password);
        Call<JsonObject> call = userService.loginUser(user);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonResponse = response.body();
                    if (jsonResponse.has("user")) {
                        callback.onSuccess(jsonResponse.get("user").getAsJsonObject().toString());
                    } else {
                        callback.onError(jsonResponse.get("message").getAsString());
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        JsonObject errorJson = new com.google.gson.Gson().fromJson(errorBody, JsonObject.class);
                        if (errorJson.has("message")) {
                            callback.onError(errorJson.get("message").getAsString());
                        } else {
                            callback.onError("Lỗi không xác định: " + response.code());
                        }
                    } catch (Exception e) {
                        callback.onError("Lỗi phản hồi: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError("Lỗi kết nối mạng: " + t.getMessage());
            }
        });
    }

    // Các interface callback cho từng chức năng
    public interface GetUsersCallback {
        void onSuccess(List<User> users);
        void onError(String error);
    }

    public interface CheckUserCallback {
        void onResult(boolean exists);
        void onError(String error);
    }

    public interface AddUserCallback {
        void onSuccess(String message);
        void onError(String error);
    }

    public interface LoginUserCallback {
        void onSuccess(String userJson);
        void onError(String error);
    }
}
