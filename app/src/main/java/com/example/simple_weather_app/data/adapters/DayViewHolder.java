package com.example.simple_weather_app.data.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_weather_app.R;

public class DayViewHolder extends RecyclerView.ViewHolder {
    private final TextView hourlyTime;
    private final TextView hourlyTemperature;
    private final TextView hourlyWeatherDescription;

    public DayViewHolder(@NonNull View itemView) {
        super(itemView);
        hourlyTime = itemView.findViewById(R.id.time);
        hourlyTemperature = itemView.findViewById(R.id.temperature);
        hourlyWeatherDescription = itemView.findViewById(R.id.weatherCode);
    }

    public TextView getHourlyTime() {
        return hourlyTime;
    }

    public TextView getHourlyTemperature() {
        return hourlyTemperature;
    }

    public TextView getHourlyWeatherDescription() {
        return hourlyWeatherDescription;
    }
}
