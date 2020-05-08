/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.isteel.myfaceit.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponseMatch;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.model.db.PlayerByNickDB;
import com.isteel.myfaceit.ui.favourites.FavouritesAdapter;
import com.isteel.myfaceit.ui.leaderBoards.LeaderAdapter;
import com.isteel.myfaceit.ui.players.PlayerAdapter;
import com.isteel.myfaceit.ui.players.profile.mapsStats.MapsAdapter;
import com.isteel.myfaceit.ui.players.profile.recentMaps.RecentMapsAdapter;
import com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats.RecentMapsStatsAdapter;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * Created by amitshekhar on 11/07/17.
 */

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"android:favourites"})
    public static void addProfileItems(RecyclerView recyclerView, List<PlayerByNickDB> playerByNicks) {
        FavouritesAdapter adapter = (FavouritesAdapter) recyclerView.getAdapter();

        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(playerByNicks);
        }
    }

    @BindingAdapter({"android:nick"})
    public static void nick(TextView blogs, String str) {
        blogs.setText(str);
    }

    @BindingAdapter({"android:players"})
    public static void addPlayerItems(RecyclerView recyclerView, List<ResponsePlayer.PlayerByNick> players) {
        PlayerAdapter adapter = (PlayerAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(players);
            recyclerView.scheduleLayoutAnimation();

        }
    }

    @BindingAdapter({"android:leaders"})
    public static void addLeaders(RecyclerView recyclerView, List<ResponsePlayer.PlayerByNick> players) {
        LeaderAdapter adapter = (LeaderAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(players);
            recyclerView.scheduleLayoutAnimation();

        }
    }

    @BindingAdapter({"android:maps"})
    public static void addMaps(RecyclerView recyclerView, List<ResponseGame.Segment> segments) {
        MapsAdapter adapter = (MapsAdapter) recyclerView.getAdapter();
        if (adapter != null && segments!=null && segments.size()!=0) {
            adapter.clearItems();
            adapter.addItems(segments);
        }
    }

    @BindingAdapter({"android:recent_maps"})
    public static void addRecentMaps(RecyclerView recyclerView, List<ResponseMatch.Match> recentMatches) {
        RecentMapsAdapter adapter = (RecentMapsAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(recentMatches);

        }
    }

    @BindingAdapter("android:maps_stats")
    public static void addRecentMapsStats(RecyclerView recyclerView, List<ResponsePlayer.Player> players) {
        RecentMapsStatsAdapter adapter = (RecentMapsStatsAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(players);

        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        if(url != null) {
            Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView);
        }else{
            Glide.with(context).load(R.drawable.ic_launcher_foreground).apply(RequestOptions.circleCropTransform()).into(imageView);

        }
    }

    @BindingAdapter("android:backImageUrl")
    public static void setImageUrlBackGround(View view, String url) {
        Context context = view.getContext();
        if(url != null) {
            Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new BlurTransformation(3,1))).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        }
    }

    @BindingAdapter("android:imageUrlMap")
    public static void setImageUrlForMaps(ImageView imageView, String url) {
        Context context = imageView.getContext();
        if(url != null) {
            Glide.with(context).load(url).into(imageView);
        }else{
            Glide.with(context).load(R.drawable.ic_launcher_foreground).into(imageView);

        }
    }

}
