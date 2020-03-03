package com.isteel.myfaceit.ui.players;

import com.isteel.myfaceit.data.model.ResponsePlayer;

public class PlayerItemViewModel {
    public final String nickname;
    public final String avatar;

    public final GameItemViewModelListener mListener;

    private final ResponsePlayer.Player mPlayer;

    public PlayerItemViewModel(GameItemViewModelListener mListener, ResponsePlayer.Player player) {
        this.mListener = mListener;
        this.mPlayer = player;
        this.avatar = mPlayer.getAvater();
        this.nickname = mPlayer.getNickName();
    }

    public void onItemClick() {
        mListener.onItemClick();
    }


    public interface GameItemViewModelListener {
        void onItemClick();
    }
}
