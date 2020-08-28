package com.mk.myweather.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqldb;

    public MyDBHelper (Context context) {
        super(context, "weatherDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "create table weatherTB (" + "id integer not null primary key autoincrement," +
                "city char(10) not null, date char(20) not null," +
                "time char(10) not null," + "temp_t char(10) not null," + "weather char(10) not null," +
                "pop char(10) not null, " + "humi char(10) not null, ws char(10) not null)";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists weatherTB");
        onCreate(sqLiteDatabase);

    }

    //sqlite db 초기화 부분
    public void initDB() {
        onUpgrade(getWritableDatabase(), 1, 2);
    }

    public void insertDB() {
        try {
            sqldb = getWritableDatabase();
            String query = String.format("insert into weatherTB (city, date, time, temp_t, weather, humi, pop, ws) " +
                            "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    "대구", "2020년 8월 28일", "10시 20분", "온도 : 36도", "흐림",
                    "습도 : 70%", "강수확률 : 80%", "풍속 : 1.7(m/s)");
            sqldb.execSQL(query);
            Log.d("jumsoon", "db데이터 정상적 입력");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqldb.close();
    }

    public void insertDB(String city ,String strDate, String time, String temp,
                         String weather, String humi, String pop, String ws) {
        try {
            sqldb = getWritableDatabase();
            String query = String.format("insert into weatherTB (" +
                            "city, date, time, temp_t, weather, humi, pop, ws) " +
                            "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    city, strDate, time, temp, weather, humi, pop, ws);
            sqldb.execSQL(query);
            Log.d("jumsoon", "db데이터 정상적 입력");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqldb.close();
    }

    public void selectDB() {
        try {
            sqldb = getWritableDatabase();
            Cursor cursor = sqldb.rawQuery("select * from weatherTB", null);

            while (cursor.moveToNext()) {
                String city = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);
                String temp = cursor.getString(4);
                String weather = cursor.getString(5);
                String humi = cursor.getString(6);
                String pop = cursor.getString(7);
                String ws = cursor.getString(8);
                Log.d("jumsoon", "도시: "+city);
                Log.d("jumsoon", "날짜: "+date);
                Log.d("jumsoon", "시간: "+time);
                Log.d("jumsoon", "온도: "+temp);
                Log.d("jumsoon", "날씨: "+weather);
                Log.d("jumsoon", "습도: "+humi);
                Log.d("jumsoon", "강수확률: "+pop);
                Log.d("jumsoon", "풍속: "+ws);
                Log.d("jumsoon", "------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqldb.close();
    }

    public void selectDB(String citydb) {
        try {
            sqldb = getWritableDatabase();
            String query = String.format("select * from weatherTB where city='%s'", citydb);
            Cursor cursor = sqldb.rawQuery(query, null);

            while (cursor.moveToNext()) {
                String city = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);
                String temp = cursor.getString(4);
                String weather = cursor.getString(5);
                String humi = cursor.getString(6);
                String pop = cursor.getString(7);
                String ws = cursor.getString(8);
                Log.d("jumsoon", "도시: "+city);
                Log.d("jumsoon", "날짜: "+date);
                Log.d("jumsoon", "시간: "+time);
                Log.d("jumsoon", "온도: "+temp);
                Log.d("jumsoon", "날씨: "+weather);
                Log.d("jumsoon", "습도: "+humi);
                Log.d("jumsoon", "강수확률: "+pop);
                Log.d("jumsoon", "풍속: "+ws);
                Log.d("jumsoon", "------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqldb.close();
    }

}
