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
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.games.GameAdapter;
import com.isteel.myfaceit.ui.players.PlayerAdapter;

import java.util.List;

/**
 * Created by amitshekhar on 11/07/17.
 */

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapter"})
    public static void addGameItems(RecyclerView recyclerView, List<ResponseGame.Game> blogs) {
        GameAdapter adapter = (GameAdapter) recyclerView.getAdapter();

        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(blogs);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addPlayerItems(RecyclerView recyclerView, List<ResponsePlayer.Player> players) {
        PlayerAdapter adapter = (PlayerAdapter) recyclerView.getAdapter();

        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(players);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

}