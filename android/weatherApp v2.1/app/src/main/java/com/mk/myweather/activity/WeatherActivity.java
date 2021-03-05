package com.mk.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mk.myweather.R;
import com.mk.myweather.model.MyWeather;
import com.mk.myweather.util.GetXmlTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WeatherActivity extends AppCompatActivity {
    TextView weatherInfo;
    Button weatherStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
//        setTitle("날씨 정보");
//
//        weatherInfo = findViewById(R.id.weatherInfo);
//        weatherStart = findViewById(R.id.startWeather);
////        weatherStart.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                GetXmlTask task =
//                        new GetXmlTask();
//                task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=89&gridy=90");
//            }
//        });
    }
}