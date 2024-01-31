package com.example.simple_weather_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simple_weather_app.R;
import com.example.simple_weather_app.data.Contract;
import com.example.simple_weather_app.data.Model;
import com.example.simple_weather_app.data.Presenter;
import com.example.simple_weather_app.data.adapters.DayAdapter;
import com.example.simple_weather_app.data.adapters.WeekAdapter;
import com.example.simple_weather_app.data.pojo.Current;
import com.example.simple_weather_app.data.pojo.Day;
import com.example.simple_weather_app.data.pojo.Forecast;
import com.example.simple_weather_app.data.pojo.Week;
import com.example.simple_weather_app.data.utils.StringUtils;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(new Model(this), this, this, this);
        presenter.requestLocationPermission();
    }

    @Override
    public void hideProgressBar() {
        ConstraintLayout progressBar = findViewById(R.id.progress_bar_layout);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCurrentForecast(String cityName, Current current) {
        TextView cityNameTV = findViewById(R.id.city_name);
        cityNameTV.setText(cityName);
        TextView temperatureTV = findViewById(R.id.temperature);
        String temperature = StringUtils.formatTemperature(current.getTemperature());
        temperatureTV.setText(temperature);
        TextView weatherDescriptionTV = findViewById(R.id.weather_description);
        String weatherDescription = Forecast.WEATHER_DESCRIPTION.get(current.getWeatherCode());
        weatherDescriptionTV.setText(weatherDescription);
    }

    @Override
    public void setDayForecast(Day day) {
        RecyclerView dayRV = findViewById(R.id.day_forecast);
        DayAdapter adapter = new DayAdapter(day);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        dayRV.setAdapter(adapter);
        dayRV.setLayoutManager(layoutManager);
    }

    @Override
    public void setWeekForecast(Week week) {
        RecyclerView weekRV = findViewById(R.id.week_forecast);
        WeekAdapter adapter = new WeekAdapter(week);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        weekRV.setAdapter(adapter);
        weekRV.setLayoutManager(layoutManager);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.handleRequestLocationPermissionResult(requestCode, permissions, grantResults);
    }
}