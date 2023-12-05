package com.example.weather_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenMeteoService {
    String BASE_URL = "https://api.open-meteo.com/";
    String TEMPERATURE = "temperature_2m";
    String MAX_TEMPERATURE = "temperature_2m_max";
    String MIN_TEMPERATURE = "temperature_2m_min";
    String WEATHER_CODE = "weather_code";
    String WIND_SPEED = "wind_speed_10m";
    String HUMIDITY = "relative_humidity_2m";
    String PRESSURE = "surface_pressure";

    @GET("v1/forecast")
    Call<Forecast> getCurrentForecast(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("current") String temperature,
            @Query("current") String humidity,
            @Query("current") String weatherCode,
            @Query("current") String pressure,
            @Query("current") String widSpeed
    );

    @GET("v1/forecast")
    Call<Forecast> getHourlyForecast(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("hourly") String temperature,
            @Query("hourly") String pressure,
            @Query("hourly") String windSpeed,
            @Query("hourly") String humidity
    );

    @GET("v1/forecast")
    Call<Forecast> getDailyForecast(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("daily") String maxTemperature,
            @Query("daily") String minTemperature,
            @Query("daily") String weatherCode
    );
}
