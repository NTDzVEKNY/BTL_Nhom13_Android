package com.example.qlnhahangculcat.database.service;

import com.example.qlnhahangculcat.model.User;
import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface UserService {
    /**
     * Gửi yêu cầu GET để lấy danh sách tất cả người dùng.
     * @return Một đối tượng Call chứa danh sách các đối tượng User.
     */
    @GET("user_api.php")
    Call<List<User>> getUsers();

    /**
     * Gửi yêu cầu GET để kiểm tra sự tồn tại của một tên đăng nhập.
     * @param username Tên đăng nhập cần kiểm tra.
     * @return Một đối tượng Call<JsonObject> chứa kết quả JSON.
     */
    @GET("user_api.php")
    Call<JsonObject> checkIfUserExists(@Query("username") String username);

    /**
     * Gửi yêu cầu POST để thêm người dùng mới.
     * @param user Đối tượng User chứa fullname, username và password.
     * @return Một đối tượng Call<JsonObject> để xử lý phản hồi từ server.
     */
    @POST("user_api.php")
    Call<JsonObject> addUser(@Body User user);

    /**
     * Gửi yêu cầu POST để đăng nhập người dùng.
     * @param user Đối tượng User chỉ chứa username và password.
     * @return Một đối tượng Call<JsonObject> để xử lý phản hồi từ server.
     */
    @POST("user_api.php?action=login")
    Call<JsonObject> loginUser(@Body User user);
}
