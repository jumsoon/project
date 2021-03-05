package com.mk.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.LineChart;

import com.mk.myweather.R;

import java.util.ArrayList;

public class WeatherDetail extends AppCompatActivity {

    TextView date, temp, reh, pop, ws, wtkor;
    ImageView w_image;
    LinearLayout w_back;

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        lineChart = findViewById(R.id.chart);

        ArrayList<Entry> entry_chart = new ArrayList<>();
        entry_chart.add(new Entry(1, 1));
        entry_chart.add(new Entry(2, 3));
        entry_chart.add(new Entry(4, 5));
        entry_chart.add(new Entry(5, 1));
        entry_chart.add(new Entry(4, 2));

        LineDataSet lineDataSet = new LineDataSet(entry_chart, "속성명");
        lineDataSet.setLineWidth(3);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFBF00"));
        lineDataSet.setColor(Color.parseColor("#F5DA81"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);


        Intent intent = getIntent();

        String city = intent.getStringExtra("city");
        setTitle(city + " 날씨 상세 정보");

        date = findViewById(R.id.w_date);
        temp = findViewById(R.id.w_temp);
        reh = findViewById(R.id.w_reh);
        pop = findViewById(R.id.w_pop);
        wtkor = findViewById(R.id.w_wtkor);
        ws = findViewById(R.id.w_ws);
        w_image = findViewById(R.id.w_image);
        w_back = findViewById(R.id.m_back);

        date.setText(intent.getStringExtra("datetime"));
        temp.setText(intent.getStringExtra("temp"));
        reh.setText("습도 "+intent.getStringExtra("humi")+"%");
        pop.setText("강수 확률 "+ intent.getStringExtra("pop")+ "%");
        wtkor.setText(intent.getStringExtra("wfkor"));
        ws.setText("풍속 "+intent.getStringExtra("ws")+"(m/s)");

        String weather = intent.getStringExtra("wfkor");
        switch (weather) {
            case "맑음":
                w_back.setBackgroundResource(R.drawable.b_sunny);
                w_image.setImageResource(R.drawable.w_sunny);

                break;
            case "구름 조금":
                w_back.setBackgroundResource(R.drawable.b_cloud);
                w_image.setImageResource(R.drawable.w_s_cloud);
                break;
            case "흐림":
                w_back.setBackgroundResource(R.drawable.b_cloud);
                w_image.setImageResource(R.drawable.w_cloud);
                break;
            case "구름 많음":
                w_back.setBackgroundResource(R.drawable.b_cloudy);
                w_image.setImageResource(R.drawable.w_deep_cloud);
                break;
            case "비":
                w_back.setBackgroundResource(R.drawable.b_rainy);
                w_image.setImageResource(R.drawable.w_rainy);
                break;
            case "눈":
                w_back.setBackgroundResource(R.drawable.b_snowy);
                w_image.setImageResource(R.drawable.w_snow);
                break;
            case "눈/비":
                w_back.setBackgroundResource(R.drawable.b_snow);
                w_image.setImageResource(R.drawable.w_snow_rain);
                break;
            case "소나기":
                w_back.setBackgroundResource(R.drawable.b_rainy);
                w_image.setImageResource(R.drawable.w_rainy);
                break;
        }







    }

}