package com.example.simple_weather_app.data.pojo;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("time")
    private String[] time;

    @SerializedName("temperature_2m")
    private double[] temperature;

    @SerializedName("weather_code")
    private int[] weatherCode;

    public String[] getTime() {
        return time;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public int[] getWeatherCode() {
        return weatherCode;
    }
}
