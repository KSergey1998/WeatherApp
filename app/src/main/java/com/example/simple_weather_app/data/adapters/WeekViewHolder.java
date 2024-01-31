package com.example.simple_weather_app.data.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_weather_app.R;

public class WeekViewHolder extends RecyclerView.ViewHolder {
    private final TextView date;

    private final TextView temperature;

    private final TextView weatherDescription;

    public WeekViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        temperature = itemView.findViewById(R.id.temperature);
        weatherDescription = itemView.findViewById(R.id.weatherCode);
    }

    public TextView getDate() {
        return date;
    }

    public TextView getTemperature() {
        return temperature;
    }

    public TextView getWeatherDescription() {
        return weatherDescription;
    }
}
