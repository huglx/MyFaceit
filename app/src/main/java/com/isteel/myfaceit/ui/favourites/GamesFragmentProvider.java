package com.isteel.myfaceit.ui.favourites;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GamesFragmentProvider {

    @ContributesAndroidInjector
    abstract GamesFragment providePlayerFragmentFactory();
}
