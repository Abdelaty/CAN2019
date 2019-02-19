package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.POJO.Matches.Example;
import com.example.myapplication.R;

import java.util.ArrayList;

public class YesterdayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yesterday, container, false);
        //ArrayList<Example> todayMatchesList = getIntent().getParcelableArrayListExtra("steps");

    }
}
