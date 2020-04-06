package com.isteel.myfaceit.ui.players;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerModule {
    /*@Provides
    PlayerAdapter providePlayerAdapter() {
        return new PlayerAdapter(new ArrayList<>());
    }
*/
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }


}
