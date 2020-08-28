package com.mk.myweather.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mk.myweather.R;
import com.mk.myweather.db.MyDBHelper;
import com.mk.myweather.fragment.WeatherFragment;
import com.mk.myweather.fragment.WeatherFragment3;
import com.mk.myweather.util.GetXmlTask;
import com.mk.myweather.util.NetworkService;
import com.mk.myweather.util.NetworkUtill;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnView;
    Fragment weatherFragment;
    Fragment weatherSettings;
    Fragment weatherFragment3;
    MyDBHelper myDBHelper;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("전국 날씨정보 v2.1");

        String str = NetworkUtill.getConnectedStatusString(MainActivity.this);
        if(str.equals("no_network")) {
            Toast.makeText(MainActivity.this, "네트워크 연결을 확인하세요",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        weatherFragment = new WeatherFragment();
        weatherSettings = new SettingsFragment2();
        weatherFragment3 = new WeatherFragment3();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, weatherFragment)
                .commit();

        bnView = findViewById(R.id.bottom_navigation);
        bnView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_home:
                        Toast.makeText(MainActivity.this,
                                "홈 메뉴", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, weatherFragment)
                                .commit();
                        return true;
                    case R.id.tab_deagu:
                        Toast.makeText(MainActivity.this,
                                "날씨 메뉴", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, weatherFragment3).commit();
                        return true;
                    case R.id.tab_setting:
                        Toast.makeText(MainActivity.this,
                                "설정 메뉴", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new SettingsFragment2())
                                .commit();
//                        Intent intent = new Intent(getApplicationContext(), WeatherSettings.class);
//                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        //myDBHelper = new MyDBHelper(this);

    }

    @Override
    public void onBackPressed() {

        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0<=intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        }
        else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기를 누르면 종료됩니다.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.weather_start_menu) {
            Toast.makeText(this, "날씨 서비스 시작", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), NetworkService.class);
            intent.putExtra("setting_city", "대구광역시");
            startService(intent);

        }
        else if (id == R.id.weather_stop_menu) {
            Toast.makeText(this, "날씨 서비스 종료", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), NetworkService.class);
            stopService(intent);
        }
        else if (id == R.id.home_exit) {
            exitDialog();
        }

        return true;
    }

    public void exitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림").setMessage("앱을 종료하시겠습니까?");
        builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public static class SettingsFragment2 extends PreferenceFragmentCompat {



        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }



        @Override
        public boolean onPreferenceTreeClick(Preference preference) {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            String city;
            boolean weatherService
                    = sharedPreferences.getBoolean("setting_weather_service", false);

            if(preference.getKey().equals("setting_weather_service")) {
                Log.d("jumsoon", "동작하니? " + weatherService);

                if(weatherService == true) {
                    city = sharedPreferences.getString("setting_weather_city", "대구광역시");
                    Log.d("jumsoon", "동작하니? " + city);

                    Intent intent = new Intent(getActivity(), NetworkService.class);
                    intent.putExtra("setting_city", city);
                    getActivity().startService(intent);

                    Toast.makeText(getActivity(), "날씨 서비스 시작", Toast.LENGTH_SHORT).show();
                    getPreferenceManager().findPreference("setting_weather_city")
                            .setEnabled(false);
                }
                else {
                    Intent intent = new Intent(getActivity(), NetworkService.class);
                    getActivity().stopService(intent);
                    Toast.makeText(getActivity(), "날씨 서비스 종료", Toast.LENGTH_SHORT).show();
                    getPreferenceManager().findPreference("setting_weather_city")
                            .setEnabled(true);
                }
            }

            if (preference.getKey().equals("webpage")) {
                Intent intent = new  Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://jumsoon.dothome.co.kr"));
                startActivity(intent);
            }



            return true;
        }
    }


    public void onSeachWeather(View v) {
        switch (v.getId()) {

            case R.id.seoul:
                GetXmlTask task1 = new GetXmlTask(MainActivity.this, "서울특별시");
                Toast.makeText(this,"서울특별시", Toast.LENGTH_SHORT).show();
                task1.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=60&gridy=127");
                break;
            case R.id.kyongki:
                GetXmlTask task2 = new GetXmlTask(MainActivity.this, "경기도");
                Toast.makeText(this,"경기도", Toast.LENGTH_SHORT).show();
                task2.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=60&gridy=120");
                break;
            case R.id.kangwon:
                GetXmlTask task3 = new GetXmlTask(MainActivity.this, "강원도");
                Toast.makeText(this,"강원도", Toast.LENGTH_SHORT).show();
                task3.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=73&gridy=134");
                break;
            case R.id.kyongnam:
                GetXmlTask task4 = new GetXmlTask(MainActivity.this, "경상남도");
                Toast.makeText(this,"경상남도", Toast.LENGTH_SHORT).show();
                task4.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=91&gridy=77");
                break;
            case R.id.kyongpook:
                GetXmlTask task5 = new GetXmlTask(MainActivity.this, "경상북도");
                Toast.makeText(this,"경상북도", Toast.LENGTH_SHORT).show();
                task5.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=89&gridy=91");
                break;
            case R.id.kangju:
                GetXmlTask task6 = new GetXmlTask(MainActivity.this, "광주광역시");
                Toast.makeText(this,"광주광역시", Toast.LENGTH_SHORT).show();
                task6.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=58&gridy=74");
                break;
            case R.id.chungpook:
                GetXmlTask task7 = new GetXmlTask(MainActivity.this, "충청북도");
                Toast.makeText(this,"충청북도", Toast.LENGTH_SHORT).show();
                task7.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=69&gridy=107");
                break;
            case R.id.chungnam:
                GetXmlTask task8 = new GetXmlTask(MainActivity.this, "충청남도");
                Toast.makeText(this,"충청남도", Toast.LENGTH_SHORT).show();
                task8.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=68&gridy=100");
                break;
            case R.id.daegu:
                GetXmlTask task9 = new GetXmlTask(MainActivity.this, "대구광역시");
                Toast.makeText(this,"대구광역시", Toast.LENGTH_SHORT).show();
                task9.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=89&gridy=90");
                break;
            case R.id.daejun:
                GetXmlTask task10 = new GetXmlTask(MainActivity.this, "대전광역시");
                Toast.makeText(this,"대전광역시", Toast.LENGTH_SHORT).show();
                task10.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=67&gridy=100");
                break;
            case R.id.ulsan:
                GetXmlTask task11 = new GetXmlTask(MainActivity.this, "울산광역시");
                Toast.makeText(this,"울산광역시", Toast.LENGTH_SHORT).show();
                task11.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=102&gridy=84");
                break;
            case R.id.pusan:
                GetXmlTask task12 = new GetXmlTask(MainActivity.this, "부산광역시");
                Toast.makeText(this,"부산광역시", Toast.LENGTH_SHORT).show();
                task12.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=98&gridy=76");
                break;
            case R.id.junnam:
                GetXmlTask task13 = new GetXmlTask(MainActivity.this, "전라남도");
                Toast.makeText(this,"전라남도", Toast.LENGTH_SHORT).show();
                task13.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=51&gridy=67");
                break;
            case R.id.junpook:
                GetXmlTask task14 = new GetXmlTask(MainActivity.this, "전라북도");
                Toast.makeText(this,"전라북도", Toast.LENGTH_SHORT).show();
                task14.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=63&gridy=89");
                break;
            case R.id.jeju:
                GetXmlTask task15 = new GetXmlTask(MainActivity.this, "제주특별자치도");
                Toast.makeText(this,"제주특별자치도", Toast.LENGTH_SHORT).show();
                task15.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=52&gridy=38");
                break;
            case R.id.inchun:
                GetXmlTask task16 = new GetXmlTask(MainActivity.this, "인천광역시");
                Toast.makeText(this,"인천광역시", Toast.LENGTH_SHORT).show();
                task16.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=55&gridy=124");
                break;

        }
    }
}