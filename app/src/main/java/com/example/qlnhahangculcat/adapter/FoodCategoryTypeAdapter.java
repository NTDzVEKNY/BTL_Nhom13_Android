package com.example.qlnhahangculcat.adapter;

import com.example.qlnhahangculcat.model.FoodCategory;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class FoodCategoryTypeAdapter extends TypeAdapter<FoodCategory> {
    @Override
    public void write(JsonWriter out, FoodCategory value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.getDisplayName());
        }
    }

    @Override
    public FoodCategory read(JsonReader in) throws IOException {
        if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String categoryString = in.nextString();
        return FoodCategory.fromString(categoryString);
    }
}