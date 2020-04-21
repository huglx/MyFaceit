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

package com.isteel.myfaceit.di.module;


import android.app.Application;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isteel.myfaceit.BuildConfig;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.AppDataManager;
import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.local.prefs.AppPreferencesHelper;
import com.isteel.myfaceit.data.local.prefs.PreferencesHelper;
import com.isteel.myfaceit.data.remote.ApiHelper;
import com.isteel.myfaceit.data.remote.ApiService;
import com.isteel.myfaceit.di.ApiInfo;
import com.isteel.myfaceit.di.PreferenceInfo;
import com.isteel.myfaceit.ui.favourites.FavouritesAdapter;
import com.isteel.myfaceit.ui.leaderBoards.LeaderAdapter;
import com.isteel.myfaceit.ui.players.PlayerAdapter;
import com.isteel.myfaceit.utils.rx.AppSchedulerProvider;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }




    @Provides
    ViewModelProviderFactory viewModelProviderFactory(DataManager dataManager,
                                                      SchedulerProvider schedulerProvider) {
        return new ViewModelProviderFactory(dataManager,
                schedulerProvider);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return "pref";
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiService provideApiHelper(ApiHelper appApiHelper) {
        return appApiHelper;
    }
    @Provides
    FavouritesAdapter provideGameAdapter() {
        return new FavouritesAdapter(new ArrayList<>());
    }

    @Provides
    PlayerAdapter providePlayerAdapter() {
        return new PlayerAdapter(new ArrayList<>());
    }

    @Provides
    LeaderAdapter provideLeaderAdapter() {
        return new LeaderAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
