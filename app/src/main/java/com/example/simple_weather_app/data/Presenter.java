package com.example.simple_weather_app.data;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.simple_weather_app.R;
import com.example.simple_weather_app.data.pojo.Current;
import com.example.simple_weather_app.data.pojo.Day;
import com.example.simple_weather_app.data.pojo.Forecast;
import com.example.simple_weather_app.data.pojo.Week;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements Contract.Presenter {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 101;
    private static final String LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    private final Contract.Model model;
    private final Contract.View view;
    private final Context context;
    private final Activity activity;

    public Presenter(Contract.Model model, Contract.View view, Context context, Activity activity) {
        this.model = model;
        this.view = view;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(context, LOCATION_PERMISSION) ==
                PackageManager.PERMISSION_GRANTED) {
            setForecast();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.location_permission_explanation);
            builder.setPositiveButton(R.string.alert_dialog_positive_btn, (dialogInterface, i) ->
                    ActivityCompat.requestPermissions(activity, new String[]{LOCATION_PERMISSION},
                            LOCATION_PERMISSION_REQUEST_CODE));
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void handleRequestLocationPermissionResult(int requestCode, String[] permissions,
                                                      int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setForecast();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.permission_denied_message);
                builder.setPositiveButton(R.string.alert_dialog_positive_btn,
                        (dialogInterface, i) -> activity.finish());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }

    private void setForecast() {
        model.getForecast(new Callback<Forecast>() {
            @Override
            public void onResponse(@NonNull Call<Forecast> call,
                                   @NonNull Response<Forecast> response) {
                if (response.isSuccessful()) {
                    Forecast forecast = response.body();
                    assert forecast != null;
                    double latitude = forecast.getLatitude();
                    double longitude = forecast.getLongitude();
                    String cityName = model.getCityName(latitude, longitude);
                    Current current = Objects.requireNonNull(forecast).getCurrent();
                    view.setCurrentForecast(cityName, current);
                    Day day = forecast.getDay();
                    view.setDayForecast(day);
                    Week week = forecast.getWeek();
                    view.setWeekForecast(week);
                    view.hideProgressBar();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    String message = response.code() + " " + response.message();
                    builder.setMessage(message);
                    builder.setPositiveButton(R.string.alert_dialog_positive_btn,
                            (dialogInterface, i) -> activity.finish());
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Forecast> call, @NonNull Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(t.getMessage());
                builder.setPositiveButton(R.string.alert_dialog_positive_btn,
                        (dialogInterface, i) -> activity.finish());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
