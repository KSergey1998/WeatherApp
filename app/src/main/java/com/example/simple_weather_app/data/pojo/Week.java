package com.example.simple_weather_app.data.pojo;

import com.google.gson.annotations.SerializedName;

public class Week {
    @SerializedName("time")
    private String[] date;

    @SerializedName("temperature_2m_max")
    private double[] maxTemperature;

    @SerializedName("temperature_2m_min")
    private double[] minTemperature;

    @SerializedName("weather_code")
    private int[] weatherCode;

    public String[] getDate() {
        return date;
    }

    public double[] getMaxTemperature() {
        return maxTemperature;
    }

    public double[] getMinTemperature() {
        return minTemperature;
    }

    public int[] getWeatherCode() {
        return weatherCode;
    }
}
