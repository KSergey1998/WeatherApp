package com.example.simple_weather_app.data.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class StringUtils {
    private StringUtils() {
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, dd", Locale.ENGLISH);
        Date unformatedDate;

        try {
            unformatedDate = inputFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        date = outputFormat.format(Objects.requireNonNull(unformatedDate));
        return date;
    }

    public static String formatTime(String time) {
        String[] formatedString = time.split("T");
        return formatedString[1];
    }

    public static String formatTemperature(double temperature) {
        int roundedTemperature = (int) Math.round(temperature);
        String formattedTemperature = String.valueOf(roundedTemperature);
        formattedTemperature += "°";
        return formattedTemperature;
    }
}
