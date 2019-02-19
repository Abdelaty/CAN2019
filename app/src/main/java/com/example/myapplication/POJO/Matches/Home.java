
package com.example.myapplication.POJO.Matches;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home implements Parcelable {

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

    protected Home(Parcel in) {
    }

    public static final Creator<Home> CREATOR = new Creator<Home>() {
        @Override
        public Home createFromParcel(Parcel in) {
            return new Home(in);
        }

        @Override
        public Home[] newArray(int size) {
            return new Home[size];
        }
    };

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
