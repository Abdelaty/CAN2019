package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.POJO.ImageModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StadiumActivity extends AppCompatActivity {
    RecyclerView stadiums_rv;
    HorizontalAdapter stadiumAdapter;
    private List<ImageModel> imageModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staduim);
        setTitle("Stadiums");

        stadiums_rv= (RecyclerView) findViewById(R.id.stadiums_rv);

        imageModels = fill_with_data();


        stadiumAdapter=new HorizontalAdapter(imageModels, getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(StadiumActivity.this, LinearLayoutManager.VERTICAL, false);
        stadiums_rv.setLayoutManager(horizontalLayoutManager);
        stadiums_rv.setAdapter(stadiumAdapter);

    }

    public List<ImageModel> fill_with_data() {

        List<ImageModel> data = new ArrayList<>();

        data.add(new ImageModel( R.drawable.cairo_stadium, "Cairo Stadium"));
        data.add(new ImageModel( R.drawable.suez_staduim, "Suez Stadium"));
        data.add(new ImageModel( R.drawable.asmailia_staduim, "Ismaliaa Stadium"));
        data.add(new ImageModel( R.drawable.burg_alarab_staduim, "Alexandria Stadium"));
        data.add(new ImageModel( R.drawable.bor_saeed_staduim, "Bor Saeed Stadium"));
        data.add(new ImageModel( R.drawable.air_staduim, "Air Forces Stadium"));



        return data;
    }

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


        List<ImageModel> stadiumList = Collections.emptyList();
        Context context;


        public HorizontalAdapter(List<ImageModel> horizontalList, Context context) {
            this.stadiumList = horizontalList;
            this.context = context;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView stadiumImage;
            TextView stadiumTitle;
            public MyViewHolder(View view) {
                super(view);
                stadiumImage=(ImageView) view.findViewById(R.id.stadium_image);
                stadiumTitle=(TextView) view.findViewById(R.id.stadium_title);
            }
        }



        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stadiums_item_rv, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.stadiumImage.setImageResource(stadiumList.get(position).imageId);
            holder.stadiumTitle.setText(stadiumList.get(position).txt);

            holder.stadiumImage.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    String list = stadiumList.get(position).txt.toString();
                    Toast.makeText(StadiumActivity.this, list, Toast.LENGTH_SHORT).show();
                }

            });

        }


        @Override
        public int getItemCount()
        {
            return stadiumList.size();
        }
    }
}
