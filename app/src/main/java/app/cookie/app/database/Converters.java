package app.cookie.app.database;


import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.cookie.app.model.Ingredient;
import app.cookie.app.model.Step;

public class Converters {

    @TypeConverter
    public static List<Ingredient> ingredientsStringToList(String value) {
        Type listType = new TypeToken<List<Ingredient>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String ingredientsListToString(List<Ingredient> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static List<Step> stepsStringToList(String value) {
        Type listType = new TypeToken<List<Step>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String stepsListToString(List<Step> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
