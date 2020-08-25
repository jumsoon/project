package com.mk.myweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtill {

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectedStatus(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activiNetwork = cm.getActiveNetworkInfo();

        if(null != activiNetwork) {
            if(activiNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return TYPE_WIFI;
            }
            else if (activiNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return TYPE_MOBILE;
            }
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectedStatusString(Context context) {

        int conn = NetworkUtill.getConnectedStatus(context);
        String status = null;
        if (conn == NetworkUtill.TYPE_WIFI) {
            status = "wifi";
        }
        else if (conn == NetworkUtill.TYPE_MOBILE) {
            status = "mobile";
        }
        else if (conn == NetworkUtill.TYPE_NOT_CONNECTED) {
            status = "no_network";
        }

        return status;
    }
}
