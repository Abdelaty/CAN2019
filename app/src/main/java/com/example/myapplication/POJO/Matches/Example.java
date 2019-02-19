
package com.example.myapplication.POJO.Matches;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example implements Parcelable {

    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("league_id")
    @Expose
    private String leagueId;
    @SerializedName("league_name")
    @Expose
    private String leagueName;
    @SerializedName("match_date")
    @Expose
    private String matchDate;
    @SerializedName("match_status")
    @Expose
    private String matchStatus;
    @SerializedName("match_time")
    @Expose
    private String matchTime;
    @SerializedName("match_hometeam_name")
    @Expose
    private String matchHometeamName;
    @SerializedName("match_hometeam_score")
    @Expose
    private String matchHometeamScore;
    @SerializedName("match_awayteam_name")
    @Expose
    private String matchAwayteamName;
    @SerializedName("match_awayteam_score")
    @Expose
    private String matchAwayteamScore;
    @SerializedName("match_hometeam_halftime_score")
    @Expose
    private String matchHometeamHalftimeScore;
    @SerializedName("match_awayteam_halftime_score")
    @Expose
    private String matchAwayteamHalftimeScore;
    @SerializedName("match_hometeam_extra_score")
    @Expose
    private String matchHometeamExtraScore;
    @SerializedName("match_awayteam_extra_score")
    @Expose
    private String matchAwayteamExtraScore;
    @SerializedName("match_hometeam_penalty_score")
    @Expose
    private String matchHometeamPenaltyScore;
    @SerializedName("match_awayteam_penalty_score")
    @Expose
    private String matchAwayteamPenaltyScore;
    @SerializedName("match_hometeam_system")
    @Expose
    private String matchHometeamSystem;
    @SerializedName("match_awayteam_system")
    @Expose
    private String matchAwayteamSystem;
    @SerializedName("match_live")
    @Expose
    private String matchLive;
    @SerializedName("goalscorer")
    @Expose
    private List<Object> goalscorer = null;
    @SerializedName("cards")
    @Expose
    private List<Object> cards = null;
    @SerializedName("lineup")
    @Expose
    private Lineup lineup;
    @SerializedName("statistics")
    @Expose
    private List<Object> statistics = null;

    protected Example(Parcel in) {
        matchId = in.readString();
        countryId = in.readString();
        countryName = in.readString();
        leagueId = in.readString();
        leagueName = in.readString();
        matchDate = in.readString();
        matchStatus = in.readString();
        matchTime = in.readString();
        matchHometeamName = in.readString();
        matchHometeamScore = in.readString();
        matchAwayteamName = in.readString();
        matchAwayteamScore = in.readString();
        matchHometeamHalftimeScore = in.readString();
        matchAwayteamHalftimeScore = in.readString();
        matchHometeamExtraScore = in.readString();
        matchAwayteamExtraScore = in.readString();
        matchHometeamPenaltyScore = in.readString();
        matchAwayteamPenaltyScore = in.readString();
        matchHometeamSystem = in.readString();
        matchAwayteamSystem = in.readString();
        matchLive = in.readString();
        lineup = in.readParcelable(Lineup.class.getClassLoader());
    }

    public static final Creator<Example> CREATOR = new Creator<Example>() {
        @Override
        public Example createFromParcel(Parcel in) {
            return new Example(in);
        }

        @Override
        public Example[] newArray(int size) {
            return new Example[size];
        }
    };

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchHometeamName() {
        return matchHometeamName;
    }

    public void setMatchHometeamName(String matchHometeamName) {
        this.matchHometeamName = matchHometeamName;
    }

    public String getMatchHometeamScore() {
        return matchHometeamScore;
    }

    public void setMatchHometeamScore(String matchHometeamScore) {
        this.matchHometeamScore = matchHometeamScore;
    }

    public String getMatchAwayteamName() {
        return matchAwayteamName;
    }

    public void setMatchAwayteamName(String matchAwayteamName) {
        this.matchAwayteamName = matchAwayteamName;
    }

    public String getMatchAwayteamScore() {
        return matchAwayteamScore;
    }

    public void setMatchAwayteamScore(String matchAwayteamScore) {
        this.matchAwayteamScore = matchAwayteamScore;
    }

    public String getMatchHometeamHalftimeScore() {
        return matchHometeamHalftimeScore;
    }

    public void setMatchHometeamHalftimeScore(String matchHometeamHalftimeScore) {
        this.matchHometeamHalftimeScore = matchHometeamHalftimeScore;
    }

    public String getMatchAwayteamHalftimeScore() {
        return matchAwayteamHalftimeScore;
    }

    public void setMatchAwayteamHalftimeScore(String matchAwayteamHalftimeScore) {
        this.matchAwayteamHalftimeScore = matchAwayteamHalftimeScore;
    }

    public String getMatchHometeamExtraScore() {
        return matchHometeamExtraScore;
    }

    public void setMatchHometeamExtraScore(String matchHometeamExtraScore) {
        this.matchHometeamExtraScore = matchHometeamExtraScore;
    }

    public String getMatchAwayteamExtraScore() {
        return matchAwayteamExtraScore;
    }

    public void setMatchAwayteamExtraScore(String matchAwayteamExtraScore) {
        this.matchAwayteamExtraScore = matchAwayteamExtraScore;
    }

    public String getMatchHometeamPenaltyScore() {
        return matchHometeamPenaltyScore;
    }

    public void setMatchHometeamPenaltyScore(String matchHometeamPenaltyScore) {
        this.matchHometeamPenaltyScore = matchHometeamPenaltyScore;
    }

    public String getMatchAwayteamPenaltyScore() {
        return matchAwayteamPenaltyScore;
    }

    public void setMatchAwayteamPenaltyScore(String matchAwayteamPenaltyScore) {
        this.matchAwayteamPenaltyScore = matchAwayteamPenaltyScore;
    }

    public String getMatchHometeamSystem() {
        return matchHometeamSystem;
    }

    public void setMatchHometeamSystem(String matchHometeamSystem) {
        this.matchHometeamSystem = matchHometeamSystem;
    }

    public String getMatchAwayteamSystem() {
        return matchAwayteamSystem;
    }

    public void setMatchAwayteamSystem(String matchAwayteamSystem) {
        this.matchAwayteamSystem = matchAwayteamSystem;
    }

    public String getMatchLive() {
        return matchLive;
    }

    public void setMatchLive(String matchLive) {
        this.matchLive = matchLive;
    }

    public List<Object> getGoalscorer() {
        return goalscorer;
    }

    public void setGoalscorer(ArrayList<Object> goalscorer) {
        this.goalscorer = goalscorer;
    }

    public List<Object> getCards() {
        return cards;
    }

    public void setCards(List<Object> cards) {
        this.cards = cards;
    }

    public Lineup getLineup() {
        return lineup;
    }

    public void setLineup(Lineup lineup) {
        this.lineup = lineup;
    }

    public List<Object> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Object> statistics) {
        this.statistics = statistics;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(matchId);
        dest.writeString(countryId);
        dest.writeString(countryName);
        dest.writeString(leagueId);
        dest.writeString(leagueName);
        dest.writeString(matchDate);
        dest.writeString(matchStatus);
        dest.writeString(matchTime);
        dest.writeString(matchHometeamName);
        dest.writeString(matchHometeamScore);
        dest.writeString(matchAwayteamName);
        dest.writeString(matchAwayteamScore);
        dest.writeString(matchHometeamHalftimeScore);
        dest.writeString(matchAwayteamHalftimeScore);
        dest.writeString(matchHometeamExtraScore);
        dest.writeString(matchAwayteamExtraScore);
        dest.writeString(matchHometeamPenaltyScore);
        dest.writeString(matchAwayteamPenaltyScore);
        dest.writeString(matchHometeamSystem);
        dest.writeString(matchAwayteamSystem);
        dest.writeString(matchLive);
        dest.writeParcelable(lineup, flags);
    }
}
