package com.isteel.myfaceit.ui.leaderBoards;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class LeaderModule {
    @Provides
    LeaderAdapter provideLeaderAdapter() {
        return new LeaderAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
