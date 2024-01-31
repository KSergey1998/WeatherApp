package com.example.simple_weather_app.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.example.simple_weather_app.data.endpoints.OpenMeteoService;
import com.example.simple_weather_app.data.pojo.Forecast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements Contract.Model {
    private final Context context;
    private final OpenMeteoService openMeteoService;

    public Model(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OpenMeteoService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openMeteoService = retrofit.create(OpenMeteoService.class);
    }

    @Override
    public String getCityName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);

        try {
            List<Address> addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1);

            if (addresses == null || addresses.isEmpty()) {
                return null;
            }

            Address address = addresses.get(0);
            return address.getLocality() + ", " + address.getCountryName();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void getForecast(Callback<Forecast> callback) {
        getLastLocation(location -> {
            Call<Forecast> call = openMeteoService.getForecast(
                    location.getLatitude(),
                    location.getLongitude(),
                    OpenMeteoService.TEMPERATURE,
                    OpenMeteoService.WEATHER_CODE,
                    OpenMeteoService.TEMPERATURE,
                    OpenMeteoService.WEATHER_CODE,
                    OpenMeteoService.HOURS,
                    OpenMeteoService.MAX_TEMPERATURE,
                    OpenMeteoService.MIN_TEMPERATURE,
                    OpenMeteoService.WEATHER_CODE);
            call.enqueue(callback);
        });
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(OnSuccessListener<Location> listener) {
        FusedLocationProviderClient fusedLocationClient = LocationServices
                .getFusedLocationProviderClient(context);
        fusedLocationClient.getLastLocation().addOnSuccessListener(listener);
    }
}
