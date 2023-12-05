package com.example.weather_app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements Contract.Presenter {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 101;
    private static final String LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    private final Contract.Model model;
    public final Contract.View view;
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
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, LOCATION_PERMISSION)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.dialog_box_location_message);
            builder.setPositiveButton(R.string.dialog_box_positive_btn, (dialogInterface, i) ->
                    ActivityCompat.requestPermissions(activity, new String[]{LOCATION_PERMISSION},
                            LOCATION_PERMISSION_REQUEST_CODE));
            builder.setNegativeButton(R.string.dialog_box_negative_btn, (dialogInterface, i) ->
                    dialogInterface.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{LOCATION_PERMISSION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void handleLocationPermissionRequestResult(int requestCode, String[] permissions,
                                                      int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                FusedLocationProviderClient fusedLocationClient =
                        LocationServices.getFusedLocationProviderClient(context);
                fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    model.getCurrentForecast(latitude, longitude, new Callback<Forecast>() {
                        @Override
                        public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                            if (response.isSuccessful()) {
                                Forecast forecast = response.body();
                                Current current = Objects.requireNonNull(forecast).getCurrent();
                                String time = current.getTime();
                                String temperature = current.getTemperature();
                                String humidity = current.getHumidity();
                                String pressure = current.getPressure();
                                String weatherCode = current.getWeatherCode();
                                String windSpeed = current.getWindSpeed();
                                Log.d("time", time);
                                Log.d("temperature", temperature);
                                Log.d("humidity", humidity);
                                Log.d("pressure", pressure);
                                Log.d("weatherCode", weatherCode);
                                Log.d("windSpeed", windSpeed);
                            } else {
                                Log.d("error", String.valueOf(response.code()));
                            }
                        }

                        @Override
                        public void onFailure(Call<Forecast> call, Throwable t) {
                            Log.d("exception", t.getMessage());
                        }
                    });

                    model.getHourlyForecast(latitude, longitude, new Callback<Forecast>() {
                        @Override
                        public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                            if (response.isSuccessful()) {
                                Forecast forecast = response.body();
                                Hourly hourly = Objects.requireNonNull(forecast).getHourly();
                                String[] time = hourly.getTime();
                                String[] temperature = hourly.getTemperature();
                                String[] humidity = hourly.getHumidity();
                                String[] pressure = hourly.getPressure();
                                String[] weatherCode = hourly.getWeatherCode();
                                String[] windSpeed = hourly.getWindSpeed();
                                Log.d("time", Arrays.toString(time));
                                Log.d("temperature", Arrays.toString(temperature));
                                Log.d("humidity", Arrays.toString(humidity));
                                Log.d("pressure", Arrays.toString(pressure));
                                Log.d("weatherCode", Arrays.toString(weatherCode));
                                Log.d("windSpeed", Arrays.toString(windSpeed));
                            } else {
                                Log.d("error", String.valueOf(response.code()));
                            }
                        }

                        @Override
                        public void onFailure(Call<Forecast> call, Throwable t) {
                            Log.d("exception", t.getMessage());
                        }
                    });

                    model.getDailyForecast(latitude, longitude, new Callback<Forecast>() {
                        @Override
                        public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                            if (response.isSuccessful()) {
                                Forecast forecast = response.body();
                                Daily daily = Objects.requireNonNull(forecast).getDaily();
                                String[] time = daily.getTime();
                                String[] maxTemperature = daily.getMaxTemperature();
                                String[] minTemperature = daily.getMinTemperature();
                                String[] weatherCode = daily.getWeatherCode();
                                Log.d("time", Arrays.toString(time));
                                Log.d("maxTemperature", Arrays.toString(maxTemperature));
                                Log.d("minTemperature", Arrays.toString(minTemperature));
                                Log.d("weatherCode", Arrays.toString(weatherCode));
                            } else {
                                Log.d("error", String.valueOf(response.code()));
                            }
                        }

                        @Override
                        public void onFailure(Call<Forecast> call, Throwable t) {
                            Log.d("exception", t.getMessage());
                        }
                    });
                });
            } else {
                // permission denied
            }
        }
    }
}
