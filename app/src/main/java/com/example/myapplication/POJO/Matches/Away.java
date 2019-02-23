package com.example.myapplication.POJO.Matches;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Away implements Parcelable {

    public static final Creator<Away> CREATOR = new Creator<Away>() {
        @Override
        public Away createFromParcel(Parcel in) {
            return new Away(in);
        }

        @Override
        public Away[] newArray(int size) {
            return new Away[size];
        }
    };
    @SerializedName("starting_lineups")
    @Expose
    private List<Object> startingLineups = null;
    @SerializedName("substitutes")
    @Expose
    private List<Object> substitutes = null;
    @SerializedName("coach")
    @Expose
    private List<Object> coach = null;
    @SerializedName("substitutions")
    @Expose
    private List<Object> substitutions = null;

    protected Away(Parcel in) {
    }

    public List<Object> getStartingLineups() {
        return startingLineups;
    }

    public void setStartingLineups(List<Object> startingLineups) {
        this.startingLineups = startingLineups;
    }

    public List<Object> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Object> substitutes) {
        this.substitutes = substitutes;
    }

    public List<Object> getCoach() {
        return coach;
    }

    public void setCoach(List<Object> coach) {
        this.coach = coach;
    }

    public List<Object> getSubstitutions() {
        return substitutions;
    }

    public void setSubstitutions(List<Object> substitutions) {
        this.substitutions = substitutions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
