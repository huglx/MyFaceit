package com.isteel.myfaceit.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMatch {
    @SerializedName("items")
    List<Match> matchList;

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
    }
}
