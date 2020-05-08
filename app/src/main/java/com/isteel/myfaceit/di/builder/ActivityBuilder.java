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

package com.isteel.myfaceit.di.builder;


import com.isteel.myfaceit.ui.favourites.FavouritesActivity;
import com.isteel.myfaceit.ui.leaderBoards.LeaderActivity;
import com.isteel.myfaceit.ui.players.profile.mapsStats.MapsFragmentProvider;
import com.isteel.myfaceit.ui.players.profile.profileInfo.ProfileInfoFragmentProvider;
import com.isteel.myfaceit.ui.players.profile.ProfileActivity;
import com.isteel.myfaceit.ui.players.PlayerActivity;
import com.isteel.myfaceit.ui.players.profile.ProfileActivityModule;
import com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats.RecentMapsStatsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules ={
            ProfileInfoFragmentProvider.class,
            MapsFragmentProvider.class,
            ProfileActivityModule.class})
    abstract ProfileActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract FavouritesActivity bindGameActivity();

    @ContributesAndroidInjector
    abstract RecentMapsStatsActivity bindRecentMapsStatsActivity();

    @ContributesAndroidInjector
    abstract LeaderActivity bindLeaderActivity();

    @ContributesAndroidInjector
    abstract PlayerActivity bindPlayerActivity();


}
