package com.isteel.myfaceit.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePlayer {
    @Expose
    @SerializedName("items")
    List<Player> items;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    public ResponsePlayer(List<Player> data) {
        this.items = data;
    }

    public List<Player> getItems() {
        return items;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public static class Player {
        @SerializedName("nickname")
        String nickName;

        @Expose
        @SerializedName("games")
        List<ResponseGame.Game> games;

        @SerializedName("avatar")
        String avater;

        public String getAvater() {
            return avater;
        }


        public List<ResponseGame.Game> getGames() {
            return games;
        }

        public String getNickName() {
            return nickName;
        }
    }

}
