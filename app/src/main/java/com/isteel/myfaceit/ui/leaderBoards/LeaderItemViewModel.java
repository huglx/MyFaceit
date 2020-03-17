package com.isteel.myfaceit.ui.leaderBoards;

import com.isteel.myfaceit.data.model.ResponsePlayer;

public class LeaderItemViewModel {
    public final String nickname;
   /* public final String avatar;
    public final String game;
    public final String lvl;*/

    public final GameItemViewModelListener mListener;

    private final ResponsePlayer.Player mPlayer;

    public LeaderItemViewModel(GameItemViewModelListener mListener, ResponsePlayer.Player player) {
        this.mListener = mListener;
        this.mPlayer = player;
        this.nickname = mPlayer.getNickName();
    }

    public void onItemClick() {
        mListener.onItemClick();
    }


    public interface GameItemViewModelListener {
        void onItemClick();
    }
}
