package com.example.myapplication.Network.SportNews;

import com.example.myapplication.POJO.Matches.Example;
import com.example.myapplication.POJO.News.NewsExample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface NEWS_API {
    @GET("top-headlines?country=eg&category=sports&apiKey=a4dd44d5d0c541c6b3ad76696dc69604")
    Call<NewsExample>  getNews();
}
//"?action=get_events&from=" + from + "&to=" + toDate + "&league_id=62&APIkey=" + API_KEY