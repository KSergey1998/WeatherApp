package com.example.simple_weather_app.data.endpoints;

import com.example.simple_weather_app.data.pojo.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenMeteoService {
    String BASE_URL = "https://api.open-meteo.com/";
    String TEMPERATURE = "temperature_2m";
    String MAX_TEMPERATURE = "temperature_2m_max";
    String MIN_TEMPERATURE = "temperature_2m_min";
    String WEATHER_CODE = "weather_code";
    String HOURS = "24";

    @GET("v1/forecast")
    Call<Forecast> getForecast(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("current") String currentTemperature,
            @Query("current") String currentWeatherCode,
            @Query("hourly") String dayTemperature,
            @Query("hourly") String dayWeatherCode,
            @Query("forecast_hours") String numberOfHours,
            @Query("daily") String weekMaxTemperature,
            @Query("daily") String weekMinTemperature,
            @Query("daily") String weekWeatherCode);
}
