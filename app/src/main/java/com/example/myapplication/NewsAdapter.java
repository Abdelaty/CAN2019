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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.myapplication.POJO.News.Article;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private NewsAdapter.OnItemClickListener mListener;
    private ArrayList<Article> newsExampleArrayList;
    private Context context;

    public NewsAdapter(ArrayList<Article> newsList, Context context) {
        this.newsExampleArrayList = newsList;
        this.context = context;
    }

    public void setOnItemClickListener(NewsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_item_rv, parent, false);

        return new NewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.MyViewHolder holder, final int position) {
//        holder.step_name.setText(stepsList.get(position).getId().toString() + "- " + stepsList.get(position).getShortDescription());
        holder.news_title.setText(newsExampleArrayList.get(position).getTitle());

        Glide.with(context).load(newsExampleArrayList.get(position).getUrlToImage()).transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.newsImage);

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

        Log.v("getItemCount", "size is: " + newsExampleArrayList.size());

        return newsExampleArrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView news_title;
        ImageView newsImage;

        MyViewHolder(View view) {
            super(view);
//            step_name = view.findViewById(R.id.item_rv);
            news_title = view.findViewById(R.id.news_title);
            newsImage = view.findViewById(R.id.news_image);
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