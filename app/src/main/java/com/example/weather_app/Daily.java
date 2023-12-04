package com.example.weather_app;

import com.google.gson.annotations.SerializedName;

public class Daily extends Forecast {
    @SerializedName("time")
    private String[] time;

    @SerializedName("temperature_2m_max")
    private String[] maxTemperature;

    @SerializedName("temperature_2m_min")
    private String[] minTemperature;

    @SerializedName("weather_code")
    private String[] weatherCode;

    public String[] getTime() {
        return time;
    }

    public String[] getMaxTemperature() {
        return maxTemperature;
    }

    public String[] getMinTemperature() {
        return minTemperature;
    }

    public String[] getWeatherCode() {
        return weatherCode;
    }
}
