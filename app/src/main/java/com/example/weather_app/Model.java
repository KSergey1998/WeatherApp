package com.example.weather_app;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements Contract.Model {
    private final OpenMeteoService openMeteoService;

    public Model() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OpenMeteoService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openMeteoService = retrofit.create(OpenMeteoService.class);
    }

    @Override
    public void getCurrentForecast(double latitude, double longitude, Callback<Forecast> callback) {
        Call<Forecast> call = openMeteoService.getCurrentForecast(
                latitude,
                longitude,
                OpenMeteoService.TEMPERATURE,
                OpenMeteoService.HUMIDITY,
                OpenMeteoService.WEATHER_CODE,
                OpenMeteoService.PRESSURE,
                OpenMeteoService.WIND_SPEED);
        call.enqueue(callback);
    }

    @Override
    public void getHourlyForecast(double latitude, double longitude, Callback<Forecast> callback) {
        Call<Forecast> call = openMeteoService.getHourlyForecast(
                latitude,
                longitude,
                OpenMeteoService.TEMPERATURE,
                OpenMeteoService.PRESSURE,
                OpenMeteoService.WIND_SPEED,
                OpenMeteoService.HUMIDITY);
        call.enqueue(callback);
    }

    @Override
    public void getDailyForecast(double latitude, double longitude, Callback<Forecast> callback) {
        Call<Forecast> call = openMeteoService.getDailyForecast(
                latitude,
                longitude,
                OpenMeteoService.MAX_TEMPERATURE,
                OpenMeteoService.MIN_TEMPERATURE,
                OpenMeteoService.WEATHER_CODE);
        call.enqueue(callback);
    }
}
