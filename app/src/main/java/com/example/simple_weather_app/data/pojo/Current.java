package com.example.simple_weather_app.data.pojo;

import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("temperature_2m")
    private double temperature;

    @SerializedName("weather_code")
    private int weatherCode;

    public double getTemperature() {
        return temperature;
    }

    public int getWeatherCode() {
        return weatherCode;
    }
}
