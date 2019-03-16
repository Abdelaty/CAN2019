package com.example.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetect {

    private static boolean chechkagain;
    private Context context;

    public ConnectionDetect(Context context) {
        this.context = context;
    }

    public boolean isConnectingToInternet() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            chechkagain = true;
            synchronized (context) {
                context.notify();
            }
        } else {
            chechkagain = false;
        }


        return chechkagain;
    }
}