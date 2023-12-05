package com.example.weather_app;

import retrofit2.Callback;

public interface Contract {
    interface Model {
        void getCurrentForecast(double latitude, double longitude, Callback<Forecast> callback);

        void getHourlyForecast(double latitude, double longitude, Callback<Forecast> callback);

        void getDailyForecast(double latitude, double longitude, Callback<Forecast> callback);
    }

    interface View {
        void setCurrentForecast();

        void setHourlyForecast();

        void setDailyForecast();
    }

    interface Presenter {
        void requestLocationPermission();

        void handleLocationPermissionRequestResult(
                int requestCode,
                String[] permissions,
                int[] grantResults);
    }
}
