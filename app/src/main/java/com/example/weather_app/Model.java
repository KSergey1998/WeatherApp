package com.example.weather_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class Model implements Contract.Model {

    @SuppressLint("MissingPermission")
    @Override
    public void getLastLocation(Activity activity) {
        FusedLocationProviderClient fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(activity);
        fusedLocationClient.getLastLocation().addOnSuccessListener(activity, location -> {
            Log.d("latitude", String.valueOf(location.getLatitude()));
            Log.d("longitude", String.valueOf(location.getLongitude()));
        });
    }
}
