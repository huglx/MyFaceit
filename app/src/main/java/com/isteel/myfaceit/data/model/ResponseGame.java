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

        @SerializedName("long_label")
        String label;

        public String getLabel() {
            return label;
        }

        public String getId() {
            return id;
        }
    }
}
