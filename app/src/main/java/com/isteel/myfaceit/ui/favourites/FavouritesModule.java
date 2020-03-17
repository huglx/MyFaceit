package com.isteel.myfaceit.ui.favourites;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouritesModule {
    @Provides
    FavouritesAdapter provideGameAdapter() {
        return new FavouritesAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
