package com.mk.myweather.model;

import java.io.Serializable;

public class MyWeather implements Serializable {
    private String date;    // 날짜
    private String time;    // 시간
    private String temp;    // 온도
    private String humi;    // 습도
    private String pop;     // 강수확률
    private String weather; // 날씨
    private String ws;      //풍속

    public MyWeather(String date, String time, String temp,
                     String humi, String pop, String weather, String ws) {
        this.date = date;
        this.time = time;
        this.temp = temp;
        this.humi = humi;
        this.pop = pop;
        this.weather = weather;
        this.ws = ws;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }
}
