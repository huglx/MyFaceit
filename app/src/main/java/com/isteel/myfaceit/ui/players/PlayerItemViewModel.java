package com.isteel.myfaceit.ui.players;


import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponsePlayer;

public class PlayerItemViewModel {
    public final ObservableField<String> nickname;
    public final ObservableField<String> avatar;

    private final ResponsePlayer.PlayerByNick mPlayer;

    public PlayerItemViewModel( ResponsePlayer.PlayerByNick player) {
        this.mPlayer = player;
        this.avatar =new ObservableField<>(mPlayer.getAvater());
        this.nickname =  new ObservableField<>(mPlayer.getNickName());
    }

}
