package com.isteel.myfaceit.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMatch {
    @SerializedName("items")
    List<Match> matchList;

    @SerializedName("rounds")
    List<Rounds> roundsList;

    @SerializedName("teams")
    Team team;

    public List<Rounds> getRoundsList() {
        return roundsList;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public static class Match{

        @SerializedName("faceit_url")
        String url;

        public String getUrl() {
            return url;
        }

        @SerializedName("match_id")
        String id;

        public String getId() {
            return id;
        }

        @SerializedName("teams")
        Team team;

        public Team getTeam() {
            return team;
        }
    }


    public static class Team {
        @SerializedName("faction2")
        Fraction fraction2;

        @SerializedName("faction1")
        Fraction fraction1;

        @SerializedName("players")
        List<ResponsePlayer.Player> players;

        public List<ResponsePlayer.Player> getPlayers() {
            return players;
        }

        public Fraction getFraction2() {
            return fraction2;
        }

        public Fraction getFraction1() {
            return fraction1;
        }
    }

    public static class Fraction {
        @SerializedName("nickname")
        String nickName;

        public String getNickName() {
            return nickName;
        }
    }

    public static class Rounds {
        @SerializedName("round_stats")
        RoundStats roundStats;

        @SerializedName("teams")
        List<Team> teams;

        public List<Team> getTeams() {
            return teams;
        }

        public RoundStats getRoundStats() {
            return roundStats;
        }
    }

    public static class RoundStats {
        @SerializedName("Map")
        String map;

        @SerializedName("Score")
        String score;

        public String getMap() {
            return map;
        }

        public String getScore() {
            return score;
        }
    }
}
