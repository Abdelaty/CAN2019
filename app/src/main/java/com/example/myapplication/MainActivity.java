package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import com.example.myapplication.Network.FootballMatches.API;
import com.example.myapplication.Network.FootballMatches.RetrofitClientInstance;
import com.example.myapplication.Network.SportNews.NEWS_API;
import com.example.myapplication.Network.SportNews.NewsRetrofitClientInstance;
import com.example.myapplication.POJO.Matches.Example;
import com.example.myapplication.POJO.News.Article;
import com.example.myapplication.POJO.News.NewsExample;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR;

public class MainActivity extends AppCompatActivity {
    static String fromDate;
    private AdView mBannerAd;
    static String toDate;
    NewsAdapter newsAdapter;
    MatchAdapter matchAdapter;
    private RecyclerView newsList_rv, matchList_rv;
    public static final String API_KEY = "ce0098152675e978d98461808e74e1283c9c8c5e7dcdd5ef39ae9014b889ed25";
    public static final String LEAUGE_ID = "63";
    ArrayList<Example> arrayList;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    ViewPager viewPager;
    private View view;

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBannerAd = (AdView) findViewById(R.id.banner_AdView);

        showBannerAd();
        setTitle("Matches Today");
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        viewPager = findViewById(R.id.item_rv);
        t = new ActionBarDrawerToggle(this, dl, R.string.drawer_open, R.string.drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newsList_rv = findViewById(R.id.news_rv);
        matchList_rv = findViewById(R.id.match_results_today_rv);
        dl.addDrawerListener(t);
        t.syncState();
        nv = (NavigationView) findViewById(R.id.nv);
        Log.v("take", getClass().getSimpleName());
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Yesterday"));
//        tabLayout.addTab(tabLayout.newTab().setText("Today"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tomorrow"));
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        final PagerAdapter adapter = new PagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setupWithViewPager(viewPager);
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//                }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.about:
                        if (getClass().getSimpleName() == "AboutActivity") {
                            break;
                        } else {

                            Toast.makeText(MainActivity.this, "about", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.settings:
                        if (getClass().getSimpleName() == "SettingActivity") {
                            break;
                        } else {

                            Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.qualifications:
                        if (getClass().getSimpleName() == "QualificationsActivity") {
                            break;
                        } else {
                            Toast.makeText(MainActivity.this, "qualifications", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.groups:
                        if (getClass().getSimpleName() == "GroupsActivity") {
                            break;
                        } else {

                            Toast.makeText(MainActivity.this, "GroupsActivity", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.booking:
                        if (getClass().getSimpleName() == "BookingActivity") {
                            break;
                        } else {

                            Toast.makeText(MainActivity.this, "BookingActivity", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.matches: {
                        Log.v("take0", getClass().getName());

                        if (getClass().getName().equals("com.example.myapplication.MainActivity$1")) {
//                            Toast.makeText(MainActivity.this, "fuk u", Toast.LENGTH_SHORT).show();

                            break;
                        } else {

                            Toast.makeText(MainActivity.this, "MainActivity", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                    case R.id.stadiums:
                        if (getClass().getSimpleName() == "StadiumsActivity") {
                            break;
                        } else {
                            Intent intent = new Intent(MainActivity.this, StadiumActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "StadiumsActivity", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.chat:
                        if (getClass().getSimpleName() == "ChatActivity") {
                            break;
                        } else {
                            Intent chatIntent = new Intent(MainActivity.this, Login.class);
                            startActivity(chatIntent);
                            Toast.makeText(MainActivity.this, "ChatActivity", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case R.id.send:
                        if (getClass().getSimpleName() == "SendActivity") {
                            break;
                        } else {

                            Toast.makeText(MainActivity.this, "SendActivity", Toast.LENGTH_SHORT).show();

                        }
                        break;
                    default:
                }
                return true;
            }
        });
        fromDate = (String)

                getYesterdayDateString();

        toDate = (String)

                getDate();
        Log.v("wrongMessage", fromDate + "////" + toDate);

        generateNetworkCall();

        generateNewsNetworkCall();
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.frame, new TodayFragment());
//        ft.commit();
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
//                Log.v("ConnectedMatch", "Downloading Data Done" + response.body().get(0).getCards());
//                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getMatchHometeamName());
//                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getMatchAwayteamName());
//                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getMatchHometeamScore());
//                Log.v("Connected", "Downloading Data Done" + response.body().get(0).getMatchAwayteamScore());
                generateMatchesList((ArrayList<Example>) response.body());
                arrayList = (ArrayList<Example>) response.body();

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
                Log.v("Connected", "Downloading Data Done" + response.body().getTotalResults());
                generateNewsList((ArrayList<Article>) response.body().getArticles());
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
//viewPager.setAdapter();
    }

    private void generateMatchesList(ArrayList<Example> matchesArrayList) {
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("today", matchesArrayList);
//        TodayFragment todayFragment = new TodayFragment();
//        todayFragment.setArguments(bundle);
//        Intent myIntent = new Intent(getApplicationContext(), TodayFragment.class);
//        myIntent.putParcelableArrayListExtra("today", matchesArrayList); //Optional parameters

//        Intent todayIntent = new Intent(this, TodayFragment.class);
//        TodayFragment fragment = new TodayFragment();
//        Bundle b = new Bundle();
//        b.putParcelableArrayList("today", matchesArrayList);
//        fragment.setArguments(b);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
//        todayIntent.putParcelableArrayListExtra("today",matchesArrayList); //Optional parameters
        matchAdapter = new MatchAdapter(matchesArrayList, getApplicationContext());
        matchList_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        matchList_rv.setAdapter(matchAdapter);

    }
    private void showBannerAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mBannerAd.loadAd(adRequest);

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
