package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DetailedMatch;
import com.example.myapplication.Fragments.TodayFragment;
import com.example.myapplication.POJO.Matches.Example;
import com.example.myapplication.POJO.Matches.Home;
import com.example.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;


public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {
    private MatchAdapter.OnItemClickListener mListener;
    private ArrayList<Example> matchArrayList;

    private Context context;
    private ArrayList<Object> goalScorerList,cardsList,awayLineupList,homeLineupList,staticsList;

    public MatchAdapter(ArrayList<Example> newsList, Context context) {
        this.matchArrayList = newsList;
        this.context = context;
    }

    public void setOnItemClickListener(MatchAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MatchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.match_item_rv, parent, false);

        return new MatchAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MatchAdapter.MyViewHolder holder, final int position) {
//        Intent todayIntent = new Intent(context, TodayFragment.class);
//        todayIntent.putParcelableArrayListExtra("today", matchArrayList); //Optional parameters
//        context.startActivity(todayIntent);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("today",matchArrayList);
        TodayFragment todayFragment = new TodayFragment();
        todayFragment.setArguments(bundle);
//        holder.step_name.setText(stepsList.get(position).getId().toString() + "- " + stepsList.get(position).getShortDescription());
        holder.homeScore_tv.setText(matchArrayList.get(position).getMatchHometeamScore());
        holder.awayName_tv.setText(matchArrayList.get(position).getMatchAwayteamName());
        holder.homeName_tv.setText(matchArrayList.get(position).getMatchHometeamName());
        holder.awayScore_tv.setText(matchArrayList.get(position).getMatchAwayteamScore());
        holder.matchTime_tv.setText(matchArrayList.get(position).getMatchTime());
        holder.statue_tv.setText(matchArrayList.get(position).getMatchStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.getAdapterPosition() + "hello", Toast.LENGTH_SHORT).show();
                homeLineupList =(ArrayList<Object>) matchArrayList.get(position).getLineup().getHome().getStartingLineups();
                awayLineupList =(ArrayList<Object>) matchArrayList.get(position).getLineup().getAway().getStartingLineups();
                goalScorerList =(ArrayList<Object>) matchArrayList.get(position).getGoalscorer();
                cardsList =(ArrayList<Object>) matchArrayList.get(position).getCards();
                staticsList =(ArrayList<Object>) matchArrayList.get(position).getStatistics();
                Intent intent = new Intent(context, DetailedMatch.class);
                Bundle args = new Bundle();
                args.putSerializable("homeLineupList",(Serializable)homeLineupList);
                args.putSerializable("awayLineupList",(Serializable)awayLineupList);
                args.putSerializable("goalScorerList",(Serializable)goalScorerList);
                args.putSerializable("cardsList",(Serializable)cardsList);
                args.putSerializable("staticsList",(Serializable)staticsList);
                intent.putExtra("BUNDLE",args);
                context.startActivity(intent);
                Log.v("homeLineup",homeLineupList.toString());
                Log.v("awayLineup",awayLineupList.toString());
                Log.v("staticsList",staticsList.toString());

                Log.v("goalScorerList",goalScorerList.toString());
                Log.v("cardsList",cardsList.toString());

//                Intent myIntent = new Intent(context, DetailedMatch.class);
//                myIntent.putExtra("homeName", "Your First Name Here");
//                myIntent.putExtra("awayName", "Your First Name Here");
//                myIntent.putExtra("homeScore", "Your First Name Here");
//                myIntent.putExtra("awayScore", "Your First Name Here");
//                myIntent.putExtra("matchTime", "Your First Name Here");
//                myIntent.putExtra("statue", "Your First Name Here");
//                myIntent.putExtra("penaltyHome", "Your First Name Here");
//                myIntent.putExtra("penaltyAway", "Your First Name Here");
//                myIntent.putStringArrayListExtra("goalScorer", matchArrayList.get(position).getGoalscorer());
//                myIntent.putStringArrayListExtra("cards",  matchArrayList.get(position).getCards());
//                Log.v("goalScorer", matchArrayList.get(position).getCards().toString());
//                Log.v("goalScorer", matchArrayList.get(position).getCards().get(0).toString());
//                myIntent.putStringArrayListExtra("homeLineup",  matchArrayList.get(position).getLineup().getHome().getStartingLineups());
//                Log.v("goalScorer", matchArrayList.get(position).getLineup().getHome().toString());
////                myIntent.putStringArrayListExtra("awayLineup",  matchArrayList.get(position).getLineup().getAway());
//                Log.v("goalScorer", matchArrayList.get(position).getLineup().getAway().toString());
//                Log.v("goalScorer", matchArrayList.get(position).getLineup().getAway().get(0).toString());
            }
        });
    }

    @Override
    public int getItemCount() {
    return matchArrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView homeScore_tv, awayName_tv, homeName_tv, awayScore_tv, matchTime_tv, statue_tv;
        ImageView homeTeam_iv, awayTeam_iv;

        MyViewHolder(View view) {
            super(view);
            homeScore_tv = view.findViewById(R.id.home_score);
            awayName_tv = view.findViewById(R.id.away_team_name);
            homeName_tv = view.findViewById(R.id.home_team_name);
            awayScore_tv = view.findViewById(R.id.away_score);
            matchTime_tv = view.findViewById(R.id.time);
            statue_tv = view.findViewById(R.id.statue);

            homeTeam_iv = view.findViewById(R.id.home_team_img);
            awayTeam_iv = view.findViewById(R.id.away_team_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}