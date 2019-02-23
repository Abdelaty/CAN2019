package com.example.myapplication.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.myapplication.R;

public class SplashActivity extends Activity {
    private static final int SPLASH_SHOW_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new BackgroundSplashTask().execute();

    }

    private class BackgroundSplashTask extends AsyncTask {

        // if you want to load database, make
        // network calls, load images
        // you can do them here and remove the following
        // sleep

        // do not worry about this Thread.sleep
        // this is an async task, it will not disrupt the UI
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {      // I have just given a sleep for this thread
            try {
                Thread.sleep(SPLASH_SHOW_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent i = new Intent(SplashActivity.this,
                    MainActivity.class);
            startActivity(i);
            finish();
        }
    }


}
