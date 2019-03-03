package com.example.myapplication.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Adapters.MatchAdapter;
import com.example.myapplication.Adapters.NewsAdapter;
import com.example.myapplication.ConnectionDetect;
import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.Database.Teams;
import com.example.myapplication.Login;
import com.example.myapplication.Network.FootballMatches.API;
import com.example.myapplication.Network.FootballMatches.RetrofitClientInstance;
import com.example.myapplication.Network.SportNews.NEWS_API;
import com.example.myapplication.Network.SportNews.NewsRetrofitClientInstance;
import com.example.myapplication.POJO.Matches.Example;
import com.example.myapplication.POJO.News.Article;
import com.example.myapplication.POJO.News.NewsExample;
import com.example.myapplication.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Database.AppDatabase.getAppDatabase;


public class MainActivity extends AppCompatActivity {
    static String fromDate;
    static String toDate;
    public final String API_KEY = "ce0098152675e978d98461808e74e1283c9c8c5e7dcdd5ef39ae9014b889ed25";
    public final String LEAGUE_ID = "63";
    NewsAdapter newsAdapter;
    ArrayList<Example> matchesArrayList;
    ArrayList<Article> articleArrayList;
    MatchAdapter matchAdapter;
    ViewPager viewPager;
    private AdView mBannerAd;
    private RecyclerView newsList_rv, matchList_rv;
    private ActionBarDrawerToggle t;
    ConnectionDetect connectionDetect;
    View parentLayout;

    public static String getDate() {

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, +1);
        return dateFormat1.format(cal1.getTime());
    }

    private static Teams addTeam(AppDatabase db, Teams teams) {
        db.userDao().insertAll(teams);
        return teams;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AppDatabase db = getAppDatabase(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLayout = findViewById(R.id.activity_main);


        mBannerAd = findViewById(R.id.banner_AdView);
        newsList_rv = findViewById(R.id.news_rv);
        matchList_rv = findViewById(R.id.match_results_today_rv);
        showBannerAd();
        fromDate = getYesterdayDateString();

        toDate = getDate();


        setTitle(R.string.main_title);

        DrawerLayout dl = findViewById(R.id.activity_main);
        viewPager = findViewById(R.id.item_rv);
        t = new ActionBarDrawerToggle(this, dl, R.string.drawer_open, R.string.drawer_close);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        newsList_rv = findViewById(R.id.news_rv);
        matchList_rv = findViewById(R.id.match_results_today_rv);
        if (db.userDao().countUsers() == 0) {
            populateWithTestData(db);

        }
        dl.addDrawerListener(t);
        t.syncState();
        NavigationView nv = findViewById(R.id.nv);
//        Log.v("take", getClass().getSimpleName());
        generateNewsNetworkCall();

        generateNetworkCall();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.about:
                        if (getClass().getSimpleName().equals(getString(R.string.about_act))) {
                            break;
                        } else {
                            Toast.makeText(MainActivity.this, R.string.about, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.stadiums:
                        if (getClass().getSimpleName().equals(getString(R.string.stadium_activity))) {
                            break;
                        } else {
                            Intent stadiumIntent = new Intent(MainActivity.this, StadiumActivity.class);
                            startActivity(stadiumIntent);
                            Toast.makeText(MainActivity.this, R.string.stadium, Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.booking:
                        if (getClass().getSimpleName().equals(getString(R.string.booking_activity))) {
                            break;
                        } else {
                            Intent bookingIntent = new Intent(MainActivity.this, BookingActivity.class);
                            startActivity(bookingIntent);
                            Toast.makeText(MainActivity.this, R.string.booking_activity, Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.matches: {

                        if (getClass().getName().equals(getString(R.string.class_name))) {
                            break;
                        } else {

                            Toast.makeText(MainActivity.this, R.string.main_activity, Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;

                    case R.id.chat:
                        if (getClass().getSimpleName().equals(getString(R.string.chat_activity))) {
                            break;
                        } else {
                            Intent chatIntent = new Intent(MainActivity.this, Login.class);
                            startActivity(chatIntent);

                        }
                        break;
//                    case R.id.send:
//                        if (getClass().getSimpleName().equals(getString(R.string.send_activity))) {
//                            break;
//                        } else {
//
//
//                        }
//                        break;
                    default:
                }
                return true;
            }
        });
        checkConnection();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void showBannerAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mBannerAd.loadAd(adRequest);
    }

    private String getYesterdayDateString() {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    private void generateNewsList(ArrayList<Article> newsExampleArrayList) {
        newsAdapter = new NewsAdapter(newsExampleArrayList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        newsList_rv.setLayoutManager(layoutManager);
        newsList_rv.setAdapter(newsAdapter);
    }

    private void generateMatchesList(ArrayList<Example> matchesArrayList) {

        matchAdapter = new MatchAdapter(matchesArrayList, this);
        final LinearLayoutManager MatchesLayoutManager = new LinearLayoutManager(getApplicationContext());
        MatchesLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        matchList_rv.setLayoutManager(MatchesLayoutManager);
        matchList_rv.setAdapter(matchAdapter);
    }

    void generateNetworkCall() {
        API service = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<List<Example>> call = service.getData(fromDate, toDate, LEAGUE_ID, API_KEY);
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(@NonNull Call<List<Example>> call, @NonNull Response<List<Example>> response) {
                matchesArrayList = (ArrayList<Example>) response.body();
                generateMatchesList(matchesArrayList);

            }

            @Override
            public void onFailure(@NonNull Call<List<Example>> call, @NonNull Throwable t) {
                Log.v(getString(R.string.wrong), t.getMessage());
            }
        });
    }

    void generateNewsNetworkCall() {
        NEWS_API service1 = NewsRetrofitClientInstance.getNewsRetrofitInstance().create(NEWS_API.class);
        Call<NewsExample> call = service1.getNews();
        call.enqueue(new Callback<NewsExample>() {
            @Override
            public void onResponse(@NonNull Call<NewsExample> call, @NonNull Response<NewsExample> response) {
                articleArrayList = (ArrayList<Article>) response.body().getArticles();
                generateNewsList(articleArrayList);

            }

            @Override
            public void onFailure(@NonNull Call<NewsExample> call, @NonNull Throwable t) {
                Log.v(getString(R.string.wrong), t.getMessage());
            }
        });
    }

    private void populateWithTestData(AppDatabase db) {
        Teams westBromwich = new Teams();
        westBromwich.setTeamName(getString(R.string.team1));
        westBromwich.setImageId(R.drawable.west_bromwich);
        addTeam(db, westBromwich);

        Teams norwichCity = new Teams();
        norwichCity.setTeamName(getString(R.string.team2));
        norwichCity.setImageId(R.drawable.norwich);
        addTeam(db, norwichCity);

        Teams middlesbrough = new Teams();
        middlesbrough.setTeamName(getString(R.string.team3));
        middlesbrough.setImageId(R.drawable.middlesbrough);
        addTeam(db, middlesbrough);

        Teams leeds = new Teams();
        leeds.setTeamName(getString(R.string.team4));
        leeds.setImageId(R.drawable.leeds);
        addTeam(db, leeds);

        Teams bristol_city = new Teams();
        bristol_city.setTeamName(getString(R.string.team5));
        bristol_city.setImageId(R.drawable.bristol_city);
        addTeam(db, bristol_city);

        Teams sheffield = new Teams();
        sheffield.setTeamName(getString(R.string.team6));
        sheffield.setImageId(R.drawable.sheffield);
        addTeam(db, sheffield);

        Teams derby_county = new Teams();
        derby_county.setTeamName(getString(R.string.team7));
        derby_county.setImageId(R.drawable.derby_county);
        addTeam(db, derby_county);

        Teams birmingham_city = new Teams();
        birmingham_city.setTeamName(getString(R.string.team8));
        birmingham_city.setImageId(R.drawable.birmingham_city);
        addTeam(db, birmingham_city);

        Teams nottingham_forest = new Teams();
        nottingham_forest.setTeamName(getString(R.string.team9));
        nottingham_forest.setImageId(R.drawable.nottingham_forest);
        addTeam(db, nottingham_forest);

        Teams swansea_city = new Teams();
        swansea_city.setTeamName(getString(R.string.team10));
        swansea_city.setImageId(R.drawable.swansea_city);
        addTeam(db, swansea_city);

        Teams hull_city = new Teams();
        hull_city.setTeamName(getString(R.string.team11));
        hull_city.setImageId(R.drawable.hull_city);
        addTeam(db, hull_city);

        Teams sheffield_wednesday = new Teams();
        sheffield_wednesday.setTeamName(getString(R.string.team12));
        sheffield_wednesday.setImageId(R.drawable.sheffield_wednesday);
        addTeam(db, sheffield_wednesday);

        Teams aston_villa = new Teams();
        aston_villa.setTeamName(getString(R.string.team13));
        aston_villa.setImageId(R.drawable.aston_villa);
        addTeam(db, aston_villa);

        Teams preston_north_end = new Teams();
        preston_north_end.setTeamName(getString(R.string.team14));
        preston_north_end.setImageId(R.drawable.preston_north_end);
        addTeam(db, preston_north_end);

        Teams stoke_city = new Teams();
        stoke_city.setTeamName(getString(R.string.team15));
        stoke_city.setImageId(R.drawable.stoke_city);
        addTeam(db, stoke_city);

        Teams queens_park_rangers = new Teams();
        queens_park_rangers.setTeamName(getString(R.string.team16));
        queens_park_rangers.setImageId(R.drawable.queens_park_rangers);
        addTeam(db, queens_park_rangers);

        Teams blackburn_rovers = new Teams();
        blackburn_rovers.setTeamName(getString(R.string.team17));
        blackburn_rovers.setImageId(R.drawable.blackburn_rovers);
        addTeam(db, blackburn_rovers);

        Teams bolton_wanderers = new Teams();
        bolton_wanderers.setTeamName(getString(R.string.team18));
        bolton_wanderers.setImageId(R.drawable.bolton_wanderers);
        addTeam(db, bolton_wanderers);

        Teams reading_fc = new Teams();
        reading_fc.setTeamName(getString(R.string.team19));
        reading_fc.setImageId(R.drawable.reading_fc);
        addTeam(db, reading_fc);

        Teams brentford = new Teams();
        brentford.setTeamName(getString(R.string.team20));
        brentford.setImageId(R.drawable.brentford);
        addTeam(db, brentford);

        Teams millwall = new Teams();
        millwall.setTeamName(getString(R.string.team21));
        millwall.setImageId(R.drawable.millwall);
        addTeam(db, millwall);

        Teams wigan_athletic = new Teams();
        wigan_athletic.setTeamName(getString(R.string.team22));
        wigan_athletic.setImageId(R.drawable.wigan_athletic);
        addTeam(db, wigan_athletic);

        Teams rotherham_united = new Teams();
        rotherham_united.setTeamName(getString(R.string.team23));
        rotherham_united.setImageId(R.drawable.rotherham_united);
        addTeam(db, rotherham_united);

        Teams ipswich_town = new Teams();
        ipswich_town.setTeamName(getString(R.string.team24));
        ipswich_town.setImageId(R.drawable.ipswich_town);
        addTeam(db, ipswich_town);
    }

    //
//    private void checkConnection() {
//        boolean isConnected = ConnectionDetect.isConnected();
//        showSnack(isConnected);
//    }
//
//    private void showSnack(boolean isConnected) {
//        String message;
//        int color;
//        if (isConnected) {
//            message = "Good! Connected to Internet";
//            color = Color.WHITE;
//        } else {
//            message = "Sorry! Not connected to internet";
//            color = Color.RED;
//        }
//
//        Snackbar snackbar = Snackbar
//                .make(findViewById(R.id.snack_fab), message, Snackbar.LENGTH_LONG);
//
//        View sbView = snackbar.getView();
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(color);
//        snackbar.show();
//    }
//
    public void checkConnection() {

        if (isNetworkAvailable()) {
            Snackbar snackbar = Snackbar
                    .make(parentLayout,
                            " You are connected to the Internet",
                            Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {

            Snackbar snackbar = Snackbar
                    .make(parentLayout,
                            "Please check internet",
                            Snackbar.LENGTH_LONG);
            snackbar.show();

        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();


    }
}
