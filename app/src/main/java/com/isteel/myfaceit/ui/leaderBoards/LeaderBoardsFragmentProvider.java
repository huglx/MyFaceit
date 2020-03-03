package com.isteel.myfaceit.ui.leaderBoards;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LeaderBoardsFragmentProvider {

    @ContributesAndroidInjector
    abstract LeaderBoardsFragment providePlayerFragmentFactory();
}
