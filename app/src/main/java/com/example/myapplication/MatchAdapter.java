package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.POJO.Matches.Example;

import java.util.ArrayList;


public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {
    private MatchAdapter.OnItemClickListener mListener;
    private ArrayList<Example> matchArrayList;
    private Context context;

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
//        holder.step_name.setText(stepsList.get(position).getId().toString() + "- " + stepsList.get(position).getShortDescription());
        holder.homeScore_tv.setText(matchArrayList.get(position).getMatchHometeamScore());
        holder.awayName_tv.setText(matchArrayList.get(position).getMatchAwayteamName());
        holder.homeName_tv.setText(matchArrayList.get(position).getMatchHometeamName());
        holder.awayScore_tv.setText(matchArrayList.get(position).getMatchAwayteamScore());
        holder.matchTime_tv.setText(matchArrayList.get(position).getMatchTime());
        holder.statue_tv.setText(matchArrayList.get(position).getMatchStatus());
//
//        Glide.with(context).load(matchArrayList.get(position).getUrlToImage()).transition(DrawableTransitionOptions.withCrossFade())
//                .into(holder.newsImage);
//        Glide.with(context).load(matchArrayList.get(position).getUrlToImage()).transition(DrawableTransitionOptions.withCrossFade())
//                .into(holder.newsImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.getAdapterPosition() + "hello", Toast.LENGTH_SHORT).show();

//
//                Intent myIntent = new Intent(context, DetailsActivity.class);
//                myIntent.putExtra("position", position); //Optional parameters ing
//                myIntent.putParcelableArrayListExtra("steps", stepsList); //Optional parameters
//                Log.v(getClass().getName(), "position is:" + position);
//
//                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {

        Log.v("getItemCount", "size is: " + matchArrayList.size());

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