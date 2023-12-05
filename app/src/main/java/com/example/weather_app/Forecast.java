package com.example.weather_app;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("current")
    private Current current;

    @SerializedName("hourly")
    private Hourly hourly;

    @SerializedName("daily")
    private Daily daily;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Current getCurrent() {
        return current;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public Daily getDaily() {
        return daily;
    }
}
