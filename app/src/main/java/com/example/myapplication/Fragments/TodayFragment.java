package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapters.MatchAdapter;
import com.example.myapplication.POJO.Matches.Example;
import com.example.myapplication.R;

import java.util.ArrayList;

public class TodayFragment extends Fragment {
    MatchAdapter matchAdapter;
    private RecyclerView todayMatches_rv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today,
                container, false);
//        Log.v("helllo",matchList.get(2).getMatchAwayteamName());
        Log.v("helllo", "hehhe");

        todayMatches_rv = view.findViewById(R.id.match_results_today_rv);
//        if (getArguments() != null) {
        assert getArguments() != null;
//        ArrayList<Example> matchList = getArguments().getParcelableArrayList("today");
        ArrayList<Example> matchList1 = getActivity().getIntent().getParcelableArrayListExtra("today");

//        }
//        generateTodayMatches(matchList);
        generateTodayMatches(matchList1);

        return view;

    }

    private void generateTodayMatches(ArrayList<Example> matchList) {
        matchAdapter = new MatchAdapter(matchList, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        todayMatches_rv.setLayoutManager(layoutManager);
        todayMatches_rv.setAdapter(matchAdapter);
        matchAdapter.notifyDataSetChanged();
    }
}
