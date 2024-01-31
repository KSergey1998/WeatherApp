package com.example.simple_weather_app.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_weather_app.R;
import com.example.simple_weather_app.data.pojo.Forecast;
import com.example.simple_weather_app.data.pojo.Week;
import com.example.simple_weather_app.data.utils.StringUtils;

public class WeekAdapter extends RecyclerView.Adapter<WeekViewHolder> {
    private final Week week;

    public WeekAdapter(Week week) {
        this.week = week;
    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_week_forecast, parent, false);
        return new WeekViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        TextView dateTV = holder.getDate();
        String[] dateArr = week.getDate();
        String date = dateArr[position];
        date = StringUtils.formatDate(date);
        dateTV.setText(date);
        TextView temperatureTV = holder.getTemperature();
        double[] minTemperatureArr = week.getMinTemperature();
        double[] maxTemperatureArr = week.getMaxTemperature();
        double minTemperature = minTemperatureArr[position];
        double maxTemperature = maxTemperatureArr[position];
        String formattedMinTemperature = StringUtils.formatTemperature(minTemperature);
        String formattedMaxTemperature = StringUtils.formatTemperature(maxTemperature);
        String temperature = formattedMinTemperature + "/" + formattedMaxTemperature;
        temperatureTV.setText(temperature);
        TextView weatherDescriptionTV = holder.getWeatherDescription();
        int[] weatherCodeArr = week.getWeatherCode();
        int weatherCode = weatherCodeArr[position];
        String weatherDescription = Forecast.WEATHER_DESCRIPTION.get(weatherCode);
        weatherDescriptionTV.setText(weatherDescription);
    }

    @Override
    public int getItemCount() {
        return week.getDate().length;
    }
}
