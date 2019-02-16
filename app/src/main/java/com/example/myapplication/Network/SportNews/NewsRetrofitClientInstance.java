package com.example.myapplication.Network.SportNews;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRetrofitClientInstance {


    private static final String NEWS_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit;

    public static Retrofit getNewsRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(NEWS_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.v("RetrofitClientInstance", retrofit.toString());
        }
        Log.d("NewsUrL:", NEWS_URL);

        return retrofit;

    }


}
