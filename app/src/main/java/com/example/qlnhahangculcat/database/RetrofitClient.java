package com.example.qlnhahangculcat.database;

import com.example.qlnhahangculcat.adapter.FoodCategoryTypeAdapter;
import com.example.qlnhahangculcat.adapter.PositionTypeAdapter;
import com.example.qlnhahangculcat.model.FoodCategory;
import com.example.qlnhahangculcat.model.Position;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://dangscap.gt.tc";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Position.class, new PositionTypeAdapter())
                    .registerTypeAdapter(FoodCategory.class, new FoodCategoryTypeAdapter())
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}