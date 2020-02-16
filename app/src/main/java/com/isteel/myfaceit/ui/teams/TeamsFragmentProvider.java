package com.isteel.myfaceit.ui.teams;

import com.isteel.myfaceit.ui.players.PLayerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TeamsFragmentProvider {

    @ContributesAndroidInjector
    abstract TeamsFragment providePlayerFragmentFactory();
}
