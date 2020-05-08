package com.isteel.myfaceit.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGame {
    @Expose
    @SerializedName("items")
    List<Game> items;

    public List<Game> getItems() {
        return items;
    }

    public ResponseGame(List<Game> items) {
        this.items = items;
    }

    public static class Game {
        @Expose
        @SerializedName("csgo")
        Csgo csgo;

        @SerializedName("game_id")
        String id;

        @SerializedName("avatar")
        String avatar;

        @SerializedName("name")
        String gameName;

        @SerializedName("skill_level")
        String lvl;

        @SerializedName("faceit_elo")
        String elo;

        @SerializedName("long_label")
        String label;

        public String getGameName() {
            return gameName;
        }

        public String getLvl() {
            return lvl;
        }

        public Csgo getCsgo() {
            return csgo;
        }

        public String getLabel() {
            return label;
        }

        public String getId() {
            return id;
        }

        public String getElo() {
            return elo;
        }
    }

    public static class Csgo {
        @SerializedName("game_id")
        String id;

        @SerializedName("game_player_name")
        String game_player_name;

        @SerializedName("lifetime")
        lifetime lifetime;

        public void setSegmentList(List<Segment> segmentList) {
            this.segmentList = segmentList;
        }

        @SerializedName("segments")
        List<Segment> segmentList;

        @SerializedName("skill_level")
        String lvl;

        @SerializedName("faceit_elo")
        String elo;

        public List<Segment> getSegmentList() {
            return segmentList;
        }

        public String getGame_player_name() {
            return game_player_name;
        }

        public String getLvl() {
            return lvl;
        }

        public ResponseGame.lifetime getLifetime() {
            return lifetime;
        }

        public String getId() {
            return id;
        }

        public String getElo() {
            return elo;
        }
    }

    public static class lifetime{
        @SerializedName("Average K/D Ratio")
        String kd;

        @SerializedName("Win Rate %")
        String winRate;

        @SerializedName("maps")
        String maps;

        @SerializedName("Longest Win Streak")
        String longestWinStreak;

        @SerializedName("Average Headshots %")
        String hs;

        public String getKd() {
            return kd;
        }

        public String getLongestWinStreak() {
            return longestWinStreak;
        }

        public String getMaps() {
            return maps;
        }

        public String getWinRate() {
            return winRate;
        }

        public String getHs() {
            return hs;
        }
    }

    public static class Segment {
        @SerializedName("label")
        private String label;

        @SerializedName("stats")
        private StatsForSegment stats;

        @SerializedName("img_regular")
        private String img_regular;

        public String getImg_regular() {
            return img_regular;
        }

        public StatsForSegment getStats() {
            return stats;
        }

        public String getLabel() {
            return label;
        }
    }

    public static class StatsForSegment{
        @SerializedName("Average K/D Ratio")
        String kd;

        @SerializedName("Matches")
        String matches;

        @SerializedName("K/R Ratio")
        String kr;

        @SerializedName("Win Rate %")
        String winRate;

        public String getKd() {
            return kd;
        }

        public String getMatches() {
            return matches;
        }

        public String getKr() {
            return kr;
        }

        public String getWinRate() {
            return winRate;
        }
    }

}
