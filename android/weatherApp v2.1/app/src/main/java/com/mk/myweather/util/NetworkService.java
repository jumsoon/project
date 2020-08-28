package com.mk.myweather.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.mk.myweather.R;
import com.mk.myweather.activity.WeatherView;
import com.mk.myweather.model.MyWeather;

import java.util.ArrayList;

public class NetworkService extends Service {

    NotificationManager notificationManager;
    ServiceThread serviceThraed;
    MyServiceHandler handler;

    int NOTIFICATION_ID = 123;
    String CHANNEL_ID = "my_channel_01";
    MediaPlayer mediaPlayer;
    String city;

    public NetworkService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //안드로이드 버전체크
        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "my_channel";
            String description = "MY_CHANNEL";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[] {
                    100, 200, 300, 400, 500, 400, 300, 200, 400
            });
            notificationChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        city = intent.getStringExtra("setting_city");
        handler = new MyServiceHandler();
        serviceThraed = new ServiceThread(handler);
        serviceThraed.start();


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        serviceThraed.stopThread();
        serviceThraed = null;
    }

    public void startMedia() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.aurora);
        mediaPlayer.setLooping(false);
        //트루가 돠면 무한반복하게됨
        mediaPlayer.start();
    }

    public void city() {
        switch (city) {
            case "대구광역시":
                new GetXmlTask(handler, getApplicationContext()).execute(
                        "https://www.kma.go.kr/wid/queryDFS.jsp?gridx=89&gridy=90");
                break;
            case "서울특별시":
                new GetXmlTask(handler, getApplicationContext()).execute(
                        "https://www.kma.go.kr/wid/queryDFS.jsp?gridx=60&gridy=127");
                break;
            case "울산광역시":
                new GetXmlTask(handler, getApplicationContext()).execute(
                        "https://www.kma.go.kr/wid/queryDFS.jsp?gridx=102&gridy=84");
                break;
            case "광주광역시":
                new GetXmlTask(handler, getApplicationContext()).execute(
                        "https://www.kma.go.kr/wid/queryDFS.jsp?gridx=58&gridy=74");
                break;
            case "인천광역시":
                new GetXmlTask(handler, getApplicationContext()).execute(
                        "https://www.kma.go.kr/wid/queryDFS.jsp?gridx=55&gridy=124");
                break;
            case "대전광역시":
                new GetXmlTask(handler, getApplicationContext()).execute(
                        "https://www.kma.go.kr/wid/queryDFS.jsp?gridx=67&gridy=100");
                break;
            case "부산광역시":
                new GetXmlTask(handler, getApplicationContext()).execute(
                        "https://www.kma.go.kr/wid/queryDFS.jsp?gridx=98&gridy=76");
                break;
        }
    }

    class MyServiceHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {

            if (msg.what == 1001) {
                Log.d("jumsoon", "쓰레드 서비스 날씨 요청");
                city();
            }
            else if (msg.what == 1002) {
                startMedia();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sun);
                Log.d("jumsoon", "notification 메시지");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_sunny)
                        .setLargeIcon(bitmap)
                        .setAutoCancel(true).setContentTitle("날씨정보")
                        .setContentText("현재 "+ city +" 날씨 정보입니다.");

                Intent intent = new Intent(getApplicationContext(), WeatherView.class);
                intent.putExtra("MyWeather", (ArrayList<MyWeather>) msg.obj);
                intent.putExtra("city", city);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                stackBuilder.addParentStack(WeatherView.class);
                stackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }

        }
    }


}
