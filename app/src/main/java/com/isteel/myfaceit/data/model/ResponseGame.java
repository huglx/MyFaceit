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
        @SerializedName("game_id")
        String id;

        @SerializedName("name")
        String gameName;

        @SerializedName("skill_level")
        String lvl;

        @SerializedName("long_label")
        String label;

        public String getGameName() {
            return gameName;
        }

        public String getLvl() {
            return lvl;
        }

        public String getLabel() {
            return label;
        }

        public String getId() {
            return id;
        }
    }
}
