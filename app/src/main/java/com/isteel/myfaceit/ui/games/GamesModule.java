package com.isteel.myfaceit.ui.games;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class GamesModule {
    @Provides
    GameAdapter provideGameAdapter() {
        return new GameAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
