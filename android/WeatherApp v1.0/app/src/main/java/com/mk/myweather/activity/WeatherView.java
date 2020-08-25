package com.mk.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mk.myweather.R;
import com.mk.myweather.adapter.OnWeatherItemClickListener;
import com.mk.myweather.adapter.WeatherAdapter;
import com.mk.myweather.model.MyWeather;

import java.util.ArrayList;

public class WeatherView extends AppCompatActivity {
    WeatherAdapter weatherAdapter;
    ArrayList<MyWeather> weatherArrayList;
    String city;

    LinearLayout ba_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_view);
        weatherArrayList =
                        (ArrayList<MyWeather>) getIntent().getSerializableExtra("MyWeather");

        city = getIntent().getStringExtra("city");
        setTitle(city + " 날씨 정보");


        ba_back = findViewById(R.id.ba_back);

        switch (city) {
            case "강원도":
                ba_back.setBackgroundResource(R.drawable.ba_kangwon);
                break;
            case "경기도":
                ba_back.setBackgroundResource(R.drawable.ba_kyungki);
                break;
            case "서울특별시":
                ba_back.setBackgroundResource(R.drawable.ba_seoul);
                break;
            case "인천광역시":
                ba_back.setBackgroundResource(R.drawable.ba_inchun);
                break;
            case "경상남도":
                ba_back.setBackgroundResource(R.drawable.ba_kyungnam);
                break;
            case "경상북도":
                ba_back.setBackgroundResource(R.drawable.ba_kyungbuk);
                break;
            case "광주광역시":
                ba_back.setBackgroundResource(R.drawable.ba_kuangju);
                break;
            case "충청북도":
                ba_back.setBackgroundResource(R.drawable.ba_chungbook);
                break;
            case "충청남도":
                ba_back.setBackgroundResource(R.drawable.ba_chungnam);
                break;
            case "대구광역시":
                ba_back.setBackgroundResource(R.drawable.ba_daegu);
                break;
            case "대전광역시":
                ba_back.setBackgroundResource(R.drawable.ba_daejun);
                break;
            case "울산광역시":
                ba_back.setBackgroundResource(R.drawable.ba_ulsan);
                break;
            case "부산광역시":
                ba_back.setBackgroundResource(R.drawable.ba_busan);
                break;
            case "전라남도":
                ba_back.setBackgroundResource(R.drawable.ba_junnam);
                break;
            case "전라북도":
                ba_back.setBackgroundResource(R.drawable.ba_junbook);
                break;
            case "제주특별자치도":
                ba_back.setBackgroundResource(R.drawable.ba_jeju);
                break;
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerList);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,
                        false);
        recyclerView.setLayoutManager(layoutManager);
        weatherAdapter = new WeatherAdapter();
        for (int i=0; i<weatherArrayList.size(); i++) {
            weatherAdapter.addItem(weatherArrayList.get(i));
        }
        recyclerView.setAdapter(weatherAdapter);
        weatherAdapter.setOnItemClickListener(new OnWeatherItemClickListener() {
            @Override
            public void onItemClick(WeatherAdapter.ViewHolder holder, View view, int position) {
                MyWeather item = weatherAdapter.getItem(position);
                Log.d("WeatherView", "날짜: " + item.getDate());
                Log.d("WeatherView", "시간: " + item.getTime());
                Log.d("WeatherView", "날씨: " + item.getWeather());
                Intent intent = new Intent(getApplicationContext(), WeatherDetail.class);

                String datetime = item.getDate()+" "+item.getTime()+"시";

                intent.putExtra("datetime", datetime);
                intent.putExtra("wfkor", item.getWeather());
                intent.putExtra("temp", item.getTemp());
                intent.putExtra("humi", item.getHumi());
                intent.putExtra("pop", item.getPop());
                intent.putExtra("ws", item.getWs());
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });

    }
}