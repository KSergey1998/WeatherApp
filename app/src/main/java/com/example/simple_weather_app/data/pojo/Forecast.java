package com.example.simple_weather_app.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Forecast {
    public static final Map<Integer, String> WEATHER_DESCRIPTION = new HashMap<Integer, String>() {
        {
            put(0, "clear sky");
            put(1, "mainly clear");
            put(2, "partly cloudy");
            put(3, "overcast");
            put(45, "fog");
            put(48, "depositing fog");
            put(51, "light drizzle");
            put(53, "moderate drizzle");
            put(55, "dense intensity drizzle");
            put(56, "light freezing drizzle");
            put(57, "dense intensity freezing drizzle");
            put(61, "slight rain");
            put(63, "moderate rain");
            put(65, "heavy intensity rain");
            put(66, "light freezing rain");
            put(67, "heavy intensity freezing rain");
            put(71, "slight snow fall");
            put(73, "moderate snow fall");
            put(75, "heavy intensity snow fall");
            put(77, "snow grains");
            put(80, "slight rain showers");
            put(81, "moderate rain showers");
            put(82, "violent rain showers");
            put(85, "slight snow showers");
            put(86, "heavy snow showers");
            put(95, "thunderstorm");
            put(96, "thunderstorm with slight hail");
            put(99, "thunderstorm with heavy hail");
        }
    };

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("current")
    private Current current;

    @SerializedName("hourly")
    private Day day;

    @SerializedName("daily")
    private Week week;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Current getCurrent() {
        return current;
    }

    public Day getDay() {
        return day;
    }

    public Week getWeek() {
        return week;
    }
}
