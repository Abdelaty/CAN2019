
package com.example.myapplication.POJO.Matches;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Away {

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

}
