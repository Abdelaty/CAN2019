package com.example.myapplication.POJO.Matches;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lineup implements Parcelable {

    public static final Creator<Lineup> CREATOR = new Creator<Lineup>() {
        @Override
        public Lineup createFromParcel(Parcel in) {
            return new Lineup(in);
        }

        @Override
        public Lineup[] newArray(int size) {
            return new Lineup[size];
        }
    };
    @SerializedName("home")
    @Expose
    private Home home;
    @SerializedName("away")
    @Expose
    private Away away;

    protected Lineup(Parcel in) {
        home = in.readParcelable(Home.class.getClassLoader());
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Away getAway() {
        return away;
    }

    public void setAway(Away away) {
        this.away = away;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(home, flags);
    }
}
