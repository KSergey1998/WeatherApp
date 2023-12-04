package com.example.weather_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Contract.View {
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(new Model(), this, this, this);
        presenter.requestLocationPermission();
    }

    @Override
    public void setCurrentForecast() {

    }

    @Override
    public void setHourlyForecast() {

    }

    @Override
    public void setDailyForecast() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.handleLocationPermissionRequestResult(requestCode, permissions, grantResults);
    }
}