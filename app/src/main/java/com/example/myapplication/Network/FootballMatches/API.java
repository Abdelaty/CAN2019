package com.example.myapplication.Network.FootballMatches;

import com.example.myapplication.POJO.Matches.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface API {
    @GET("?action=get_events")
    Call<List<Example>> getData(
            @Query("from") String from,
            @Query("to") String to,
            @Query("league_id") String league_id,
            @Query("APIkey") String API_KEY
    );
}
//"?action=get_events&from=" + from + "&to=" + toDate + "&league_id=62&APIkey=" + API_KEY