package com.example.myapplication.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams")

public class Teams {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "teamName")
    private String teamName;
    @ColumnInfo(name = "imageId")
    private int imageId;

    @NonNull
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(@NonNull String teamName) {
        this.teamName = teamName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
