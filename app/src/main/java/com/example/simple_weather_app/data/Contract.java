package com.example.simple_weather_app.data;

import com.example.simple_weather_app.data.pojo.Current;
import com.example.simple_weather_app.data.pojo.Day;
import com.example.simple_weather_app.data.pojo.Forecast;
import com.example.simple_weather_app.data.pojo.Week;

import retrofit2.Callback;

public interface Contract {
    interface Model {
        String getCityName(double latitude, double longitude);

        void getForecast(Callback<Forecast> callback);
    }

    interface View {
        void hideProgressBar();

        void setCurrentForecast(String cityName, Current current);

        void setDayForecast(Day day);

        void setWeekForecast(Week week);
    }

    interface Presenter {
        void requestLocationPermission();

        void handleRequestLocationPermissionResult(
                int requestCode,
                String[] permissions,
                int[] grantResults);
    }
}
