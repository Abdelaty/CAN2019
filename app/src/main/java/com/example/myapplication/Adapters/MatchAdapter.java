package com.example.myapplication.Adapters;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activites.DetailedMatch;
import com.example.myapplication.Database.AppDatabase;
import com.example.myapplication.MatchWidget;
import com.example.myapplication.POJO.Matches.Example;
import com.example.myapplication.R;

import java.util.ArrayList;

import static com.example.myapplication.Database.AppDatabase.getAppDatabase;


public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {
    private MatchAdapter.OnItemClickListener mListener;
    private ArrayList<Example> matchArrayList;

    private Context context;
    AppDatabase db = getAppDatabase(context);
    private ArrayList<Object> goalScorerList, cardsList, awayLineupList, homeLineupList, staticsList;

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


        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("today", matchArrayList);

        holder.homeTeam_iv.setImageResource(db.userDao().getImage(matchArrayList.get(position).getMatchHometeamName()).getImageId());
        holder.awayTeam_iv.setImageResource(db.userDao().getImage(matchArrayList.get(position).getMatchAwayteamName()).getImageId());
        //if statement
        if (matchArrayList.get(position).getMatchHometeamScore() == null || matchArrayList.get(position).getMatchHometeamScore().isEmpty() || matchArrayList.get(position).getMatchHometeamScore().matches("") || matchArrayList.get(position).getMatchHometeamScore().equals("0") || matchArrayList.get(position).getMatchHometeamScore().contentEquals("0")) {
            holder.homeScore_tv.setText("00");
            Log.v("if home", matchArrayList.get(position).getMatchHometeamScore());

        } else {

            Log.v("else home", matchArrayList.get(position).getMatchHometeamScore());
            holder.homeScore_tv.setText(matchArrayList.get(position).getMatchHometeamScore());
        }
        if (matchArrayList.get(position).getMatchAwayteamScore() == null || matchArrayList.get(position).getMatchAwayteamScore().isEmpty() || matchArrayList.get(position).getMatchAwayteamScore().matches("") || matchArrayList.get(position).getMatchAwayteamScore().equals("0") || matchArrayList.get(position).getMatchAwayteamScore().contentEquals("0")) {
            holder.awayScore_tv.setText("00");
            Log.v("if away", matchArrayList.get(position).getMatchAwayteamScore());
        } else {
            Log.v("else away", matchArrayList.get(position).getMatchAwayteamScore());

            holder.awayScore_tv.setText(matchArrayList.get(position).getMatchAwayteamScore());
        }

        holder.awayName_tv.setText(matchArrayList.get(position).getMatchAwayteamName());
        holder.homeName_tv.setText(matchArrayList.get(position).getMatchHometeamName());
        holder.matchTime_tv.setText(matchArrayList.get(position).getMatchTime());
        holder.statue_tv.setText(matchArrayList.get(position).getMatchStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeLineupList = (ArrayList<Object>) matchArrayList.get(position).getLineup().getHome().getStartingLineups();
                awayLineupList = (ArrayList<Object>) matchArrayList.get(position).getLineup().getAway().getStartingLineups();
                goalScorerList = (ArrayList<Object>) matchArrayList.get(position).getGoalscorer();
                cardsList = (ArrayList<Object>) matchArrayList.get(position).getCards();
                staticsList = (ArrayList<Object>) matchArrayList.get(position).getStatistics();
                Intent intent = new Intent(context, DetailedMatch.class);

                Intent intent1 = new Intent(MatchWidget.ACTION_TEXT_CHANGED);
                intent1.putExtra("newMatch", "Next Match: \n " + matchArrayList.get(0).getMatchHometeamName() + " VS " + matchArrayList.get(0).getMatchAwayteamName() + "\n" + "Time:" + matchArrayList.get(0).getMatchDate() + "\n");
                context.sendBroadcast(intent);
                widget();


            }
        });
    }

    @Override
    public int getItemCount() {
        return matchArrayList.size();
    }

    private void widget() {
        String nextMatch;
        nextMatch = "Next Match: \n " + matchArrayList.get(0).getMatchHometeamName() + " VS " + matchArrayList.get(0).getMatchAwayteamName() + "\n" + "Time:" + matchArrayList.get(0).getMatchDate() + "\n";
        SharedPreferences preferences = context.getSharedPreferences("match", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("matchWidget", nextMatch);
        editor.apply();

        int[] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, MatchWidget.class));

        MatchWidget myWidget = new MatchWidget();
        myWidget.onUpdate(context, AppWidgetManager.getInstance(context), ids);
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