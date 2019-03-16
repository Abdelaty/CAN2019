package com.example.myapplication.Activites;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.POJO.ImageModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StadiumActivity extends AppCompatActivity {
    @BindView(R.id.stadiums_rv)
    RecyclerView stadiums_rv;

    HorizontalAdapter stadiumAdapter;
    private List<ImageModel> imageModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staduim);
        ButterKnife.bind(this);

        setTitle(R.string.stadium_main);

        imageModels = fill_with_data();
        stadiumAdapter = new HorizontalAdapter(imageModels, getApplication());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(StadiumActivity.this, LinearLayoutManager.VERTICAL, false);
        stadiums_rv.setLayoutManager(horizontalLayoutManager);
        stadiums_rv.setAdapter(stadiumAdapter);

    }

    public List<ImageModel> fill_with_data() {

        List<ImageModel> data = new ArrayList<>();

        data.add(new ImageModel(R.drawable.cairo_stadium, getString(R.string.cairo_stad)));
        data.add(new ImageModel(R.drawable.suez_staduim, getString(R.string.suez_stad)));
        data.add(new ImageModel(R.drawable.asmailia_staduim, getString(R.string.isma_stad)));
        data.add(new ImageModel(R.drawable.burg_alarab_staduim, getString(R.string.alex_stad)));
        data.add(new ImageModel(R.drawable.bor_saeed_staduim, getString(R.string.bor_saed_stad)));
        data.add(new ImageModel(R.drawable.air_staduim, getString(R.string.air_forces_stad)));


        return data;
    }

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


        List<ImageModel> stadiumList = Collections.emptyList();
        Context context;


        public HorizontalAdapter(List<ImageModel> horizontalList, Context context) {
            this.stadiumList = horizontalList;
            this.context = context;
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
                    String list = stadiumList.get(position).txt;
                    Toast.makeText(StadiumActivity.this, list, Toast.LENGTH_SHORT).show();
                }

            });

        }

        @Override
        public int getItemCount() {
            return stadiumList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView stadiumImage;
            TextView stadiumTitle;

            public MyViewHolder(View view) {
                super(view);
                stadiumImage = view.findViewById(R.id.stadium_image);
                stadiumTitle = view.findViewById(R.id.stadium_title);
            }
        }
    }
}
