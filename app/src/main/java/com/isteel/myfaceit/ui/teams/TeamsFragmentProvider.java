package com.isteel.myfaceit.ui.teams;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TeamsFragmentProvider {

    @ContributesAndroidInjector
    abstract TeamsFragment providePlayerFragmentFactory();
}
