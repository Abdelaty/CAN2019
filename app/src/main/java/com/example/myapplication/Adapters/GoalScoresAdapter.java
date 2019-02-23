//package com.example.myapplication.Adapters;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.myapplication.POJO.Matches.Example;
//import com.example.myapplication.R;
//
//import java.util.ArrayList;
//
//public class GoalScoresAdapter  extends RecyclerView.Adapter<GoalScoresAdapter.MyViewHolder> {
//    private MatchAdapter.OnItemClickListener mListener;
//    private ArrayList<Example> matchArrayList;
//
//    private Context context;
//    private ArrayList<Object> goalScorerList,cardsList,awayLineupList,homeLineupList,staticsList;
//
//    public GoalScoresAdapter(ArrayList<Example> newsList, Context context) {
//        this.matchArrayList = newsList;
//        this.context = context;
//    }
//
//    public void setOnItemClickListener(MatchAdapter.OnItemClickListener listener) {
//        mListener = listener;
//    }
//
//    @NonNull
//    @Override
//    public GoalScoresAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.match_item_rv, parent, false);
//
//        return new GoalScoresAdapter.MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final GoalScoresAdapter.MyViewHolder holder, final int position) {
//        holder.statue_tv.setText(matchArrayList.get(position).getMatchStatus());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, holder.getAdapterPosition() + "hello", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return matchArrayList.size();
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView homeScore_tv, awayName_tv, homeName_tv, awayScore_tv, matchTime_tv, statue_tv;
//        ImageView homeTeam_iv, awayTeam_iv;
//
//        MyViewHolder(View view) {
//            super(view);
//            homeTeam_iv = view.findViewById(R.id.home_team_img);
//            awayTeam_iv = view.findViewById(R.id.away_team_img);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
//                }
//            });
//        }
//    }
//}