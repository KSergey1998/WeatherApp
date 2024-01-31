package com.example.simple_weather_app.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_weather_app.R;
import com.example.simple_weather_app.data.pojo.Day;
import com.example.simple_weather_app.data.pojo.Forecast;
import com.example.simple_weather_app.data.utils.StringUtils;

public class DayAdapter extends RecyclerView.Adapter<DayViewHolder> {
    private final Day day;

    public DayAdapter(Day day) {
        this.day = day;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_day_forecast, parent, false);
        return new DayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        TextView timeTV = holder.getHourlyTime();
        String[] timeArr = day.getTime();
        String time = timeArr[position];
        time = StringUtils.formatTime(time);
        timeTV.setText(time);
        TextView temperatureTV = holder.getHourlyTemperature();
        double[] temperatureArr = day.getTemperature();
        double temperature = temperatureArr[position];
        String formattedTemperature = StringUtils.formatTemperature(temperature);
        temperatureTV.setText(formattedTemperature);
        TextView weatherDescriptionTV = holder.getHourlyWeatherDescription();
        int[] weatherCodeArr = day.getWeatherCode();
        int weatherCode = weatherCodeArr[position];
        String weatherDescription = Forecast.WEATHER_DESCRIPTION.get(weatherCode);
        weatherDescriptionTV.setText(weatherDescription);
    }

    @Override
    public int getItemCount() {
        return day.getTime().length;
    }
}
