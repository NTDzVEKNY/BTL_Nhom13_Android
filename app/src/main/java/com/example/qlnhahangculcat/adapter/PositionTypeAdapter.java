package com.example.qlnhahangculcat.adapter;

import com.example.qlnhahangculcat.model.Position;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
public class PositionTypeAdapter extends TypeAdapter<Position> {
    @Override
    public void write(JsonWriter out, Position value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            // Khi gửi dữ liệu lên server, sử dụng displayName
            out.value(value.getDisplayName());
        }
    }

    @Override
    public Position read(JsonReader in) throws IOException {
        // Khi nhận dữ liệu từ server, chuyển đổi chuỗi thành enum
        if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String positionString = in.nextString();
        // Sử dụng phương thức fromString() đã có trong enum của bạn
        return Position.fromString(positionString);
    }
}
