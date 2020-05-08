package com.isteel.myfaceit.ui.players;


import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.utils.LogUtil;

public class PlayerItemViewModel {
    public final ObservableField<String> nickname;
    public final ObservableField<String> avatar;

    private final ResponsePlayer.PlayerByNick mPlayer;

    public PlayerItemViewModel( ResponsePlayer.PlayerByNick player) {
        this.mPlayer = player;
        if(mPlayer.getAvater() == null || mPlayer.getAvater().isEmpty()){
            this.avatar = new ObservableField<>("https://corporate.faceit.com/wp-content/uploads/icon-pheasant-preview-2.png");
        } else this.avatar =new ObservableField<>(mPlayer.getAvater());
        this.nickname =  new ObservableField<>(mPlayer.getNickName());
    }
}
