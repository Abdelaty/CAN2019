package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class DetailedMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_match);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Object> homeLineupList = (ArrayList<Object>) args.getSerializable("homeLineupList");
        ArrayList<Object> awayLineupList = (ArrayList<Object>) args.getSerializable("awayLineupList");

        ArrayList<Object> goalScorerList = (ArrayList<Object>) args.getSerializable("goalScorerList");
        ArrayList<Object> cardsList = (ArrayList<Object>) args.getSerializable("cardsList");
        ArrayList<Object> staticsList = (ArrayList<Object>) args.getSerializable("staticsList");
        Log.v("staticsList", staticsList.get(0).toString());
    }
}
