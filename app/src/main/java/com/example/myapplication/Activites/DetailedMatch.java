package com.example.myapplication.Activites;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_match);
        Intent intent = getIntent();

        //   Bundle args = intent.getBundleExtra("BUNDLE");
//        ArrayList<Object> homeLineupList = (ArrayList<Object>) args.getSerializable("homeLineupList");
//        ArrayList<Object> awayLineupList = (ArrayList<Object>) args.getSerializable("awayLineupList");
//
//        ArrayList<Object> goalScorerList = (ArrayList<Object>) args.getSerializable("goalScorerList");
//        ArrayList<Object> cardsList = (ArrayList<Object>) args.getSerializable("cardsList");
//        ArrayList<Object> staticsList = (ArrayList<Object>) args.getSerializable("staticsList");
    }
}
