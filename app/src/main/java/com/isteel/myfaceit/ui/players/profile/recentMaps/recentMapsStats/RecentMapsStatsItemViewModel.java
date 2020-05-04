package com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats;


import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponsePlayer;

public class RecentMapsStatsItemViewModel {
    public final ObservableField<String> nickname;

    private final ResponsePlayer.Player mPlayer;

    public RecentMapsStatsItemViewModel(ResponsePlayer.Player player) {
        this.mPlayer = player;
        this.nickname =  new ObservableField<>(mPlayer.getNickName());
    }
}
