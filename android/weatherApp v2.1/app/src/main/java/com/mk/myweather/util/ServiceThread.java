package com.mk.myweather.util;


import android.os.Handler;

public class ServiceThread extends Thread {

    Handler handler;
    boolean isRun = true;

    public ServiceThread(Handler handler) {
        this.handler = handler;
    }


    public void stopThread() {
        synchronized (this) {
            isRun = false;
        }
    }

    @Override
    public void run() {

        while (isRun) {
            handler.sendEmptyMessage(1001);
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
