package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.Network.FootballMatches.API;
import com.example.myapplication.Network.FootballMatches.RetrofitClientInstance;
import com.example.myapplication.Network.SportNews.NEWS_API;
import com.example.myapplication.Network.SportNews.NewsRetrofitClientInstance;
import com.example.myapplication.POJO.Matches.Example;
import com.example.myapplication.POJO.News.Article;
import com.example.myapplication.POJO.News.NewsExample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    static String fromDate;
    static String toDate;
    NewsAdapter newsAdapter;
    MatchAdapter matchAdapter;
    private RecyclerView newsList_rv, matchList_rv;
    private static final String TAG = "HELLO";
    public static final String API_KEY = "ce0098152675e978d98461808e74e1283c9c8c5e7dcdd5ef39ae9014b889ed25";
    public static final String LEAUGE_ID = "63";

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.drawer_open, R.string.drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newsList_rv = findViewById(R.id.news_rv);
        matchList_rv = findViewById(R.id.match_results_rv);
        dl.addDrawerListener(t);
        t.syncState();
        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.about:
                        Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.qualifications:
                        Toast.makeText(MainActivity.this, "Qualifications", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.groups:
                        Toast.makeText(MainActivity.this, "Groups", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.booking:
                        Toast.makeText(MainActivity.this, "Booking", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.matches:
                        Toast.makeText(MainActivity.this, "Matches", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.stadiums:
                        Toast.makeText(MainActivity.this, "Stadiums", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chat:
                        Toast.makeText(MainActivity.this, "Chat", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.send:
                        Toast.makeText(MainActivity.this, "Send Suggestions", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                return true;
            }
        });
        fromDate = (String) getYesterdayDateString();
        toDate = (String) getDate();
        Log.v("wrongMessage", fromDate + "////" + toDate);

        generateNetworkCall();
        generateNewsNetworkCall();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    void generateNetworkCall() {

        API service = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<List<Example>> call = service.getData(fromDate, toDate, LEAUGE_ID, API_KEY);

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(@NonNull Call<List<Example>> call, @NonNull Response<List<Example>> response) {
                Log.v("ConnectedMatch", "Downloading Data Done" + response.body().get(0).getCards());
                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getMatchHometeamName());
                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getMatchAwayteamName());
                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getMatchHometeamScore());
                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getMatchAwayteamScore());
                generateMatchesList((ArrayList<Example>) response.body());

            }

            @Override
            public void onFailure(@NonNull Call<List<Example>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please connect to internet!", Toast.LENGTH_SHORT).show();
                Log.v("wrongMessage", t.getMessage());


            }

        });

    }

    void generateNewsNetworkCall() {

        NEWS_API service = NewsRetrofitClientInstance.getNewsRetrofitInstance().create(NEWS_API.class);
        Call<NewsExample> call = service.getNews();

        call.enqueue(new Callback<NewsExample>() {
            @Override
            public void onResponse(@NonNull Call<NewsExample> call, @NonNull Response<NewsExample> response) {
//                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getStatus());
                Log.v("Connected", "Downloading Data Done" + response.body().getTotalResults());
                generateNewsList((ArrayList<Article>) response.body().getArticles());
//                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getTotalResults());
            }

            @Override
            public void onFailure(@NonNull Call<NewsExample> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please connect to internet!", Toast.LENGTH_SHORT).show();
                Log.v("wrongMessageNews", t.getMessage());


            }

        });

    }

    public static String getDate() {

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, 1);
        return dateFormat1.format(cal1.getTime());
    }

    private String getYesterdayDateString() {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        return dateFormat.format(cal.getTime());
    }

    private void generateNewsList(ArrayList<Article> newsExampleArrayList) {
        newsAdapter = new NewsAdapter(newsExampleArrayList, getApplicationContext());
        newsList_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        newsList_rv.setAdapter(newsAdapter);

    }

    private void generateMatchesList(ArrayList<Example> matchesArrayList) {
        matchAdapter = new MatchAdapter(matchesArrayList, getApplicationContext());
        matchList_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        matchList_rv.setAdapter(matchAdapter);

    }
}
