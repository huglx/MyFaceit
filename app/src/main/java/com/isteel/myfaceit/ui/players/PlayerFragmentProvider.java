package com.isteel.myfaceit.ui.players;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlayerFragmentProvider {

    @ContributesAndroidInjector
    abstract PLayerFragment providePlayerFragmentFactory();
}
