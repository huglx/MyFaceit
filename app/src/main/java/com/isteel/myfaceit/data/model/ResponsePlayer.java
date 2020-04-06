package com.isteel.myfaceit.data.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.isteel.myfaceit.BR;

import java.util.List;

public class ResponsePlayer {
    @Expose
    @SerializedName("items")
    List<PlayerByNick> items;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    public ResponsePlayer(List<PlayerByNick> data) {
        this.items = data;
    }

    public ResponsePlayer() {

    }

    public List<PlayerByNick> getItems() {
        return items;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setItems(List<PlayerByNick> playerByNicks) {
        items = playerByNicks;
    }

    public static class Player extends BaseObservable{

        @SerializedName("nickname")
        String nickName;

        @Expose
        @SerializedName("games")
        ResponseGame.Game games;

        @SerializedName("avatar")
        String avater;

        public void setNickName(String nickName) {
            this.nickName = nickName;
            notifyPropertyChanged(BR.nickName);
        }

        public String getAvater() {
            return avater;
        }


        public ResponseGame.Game getGames() {
            return games;
        }
        @Bindable
        public String getNickName() {
            return nickName;
        }
    }

    public static class PlayerByNick extends BaseObservable{

        @SerializedName("nickname")
        String nickName;

        @Expose
        @SerializedName("games")
        List<ResponseGame.Game> games;

        @SerializedName("avatar")
        String avater;

        public void setNickName(String nickName) {
            this.nickName = nickName;
            notifyPropertyChanged(BR.nickName);
        }

        public String getAvater() {
            return avater;
        }


        public List<ResponseGame.Game> getGames() {
            return games;
        }
        @Bindable
        public String getNickName() {
            return nickName;
        }
    }

}
