package com.example.weather_app;

import com.google.gson.annotations.SerializedName;

public class Current extends Forecast {
    @SerializedName("time")
    private String time;

    @SerializedName("temperature_2m")
    private String temperature;

    @SerializedName("relative_humidity_2m")
    private String humidity;

    @SerializedName("surface_pressure")
    private String pressure;


    @SerializedName("weather_code")
    private String weatherCode;

    @SerializedName("wind_speed_10m")
    private String windSpeed;

    public String getTime() {
        return time;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public String getWindSpeed() {
        return windSpeed;
    }
}
