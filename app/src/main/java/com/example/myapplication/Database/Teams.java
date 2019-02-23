package com.example.myapplication.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "teams")

public class Teams {
//    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }

    //    @PrimaryKey(autoGenerate = true)
//    private int uid;
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
