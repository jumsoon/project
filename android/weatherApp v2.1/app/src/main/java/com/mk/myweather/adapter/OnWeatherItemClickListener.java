package com.mk.myweather.adapter;

import android.view.View;

public interface OnWeatherItemClickListener {
    public void onItemClick(WeatherAdapter.ViewHolder holder,
                            View view, int position);
}