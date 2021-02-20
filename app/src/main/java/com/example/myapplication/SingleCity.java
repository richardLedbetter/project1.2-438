package com.example.myapplication;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class SingleCity {
    // object that represents a single city

    @SerializedName("name")
    private String cityName;

    @SerializedName("id")
    private String cityID;

    @SerializedName("main")
    @JsonAdapter(mainTempDeserializer.class)
    private String mainTemperture;

    //handles getting data from nested json objects
    public static class mainTempDeserializer implements JsonDeserializer<String> {
        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            return json.getAsJsonObject().get("temp").getAsString();
        }
    }

    public String getCityName() {
        return cityName;
    }

    public String getMainTemperture() {
        return mainTemperture;
    }

    public String getCityID() {
        return cityID;
    }
}
