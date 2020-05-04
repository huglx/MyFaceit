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

package com.isteel.myfaceit.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.di.PreferenceInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_GAME = "PREF_GAME";
    private static final String PREF_PROFILE = "PREF_PROFILE";
    private List<ResponsePlayer.PlayerByNick> playerByNickList;

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        playerByNickList = new ArrayList<>();
    }

    @Override
    public String getGame() {
        return mPrefs.getString(PREF_GAME, "csgo");
    }

    @Override
    public void setGame(String game) {
        mPrefs.edit().putString(PREF_GAME, game).apply();
    }

    @Override
    public List<ResponsePlayer.PlayerByNick> getProfile() {
        return playerByNickList;
    }

    @Override
    public void setProfile(ResponsePlayer.PlayerByNick profile) {
        if(profile!=null)
        playerByNickList.add(profile);
    }
}
