package ru.tinkoff.fintech.lesson4.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private int id;
    private String name;
    private String description;

    /*public String toJson() {
        return new Gson().toJson(this);
    }

    public static Course fromJson(Object jsonObject) {
        String jsonString = new String((byte[]) jsonObject).replace("\\"+"\"", "\"");
        jsonString = jsonString.substring(1, jsonString.length() - 1);
        JSONObject jsonObj = new JSONObject(jsonString);
        return new Course(jsonObj.getString("name"), jsonObj.getString("description"));
    }*/
}
