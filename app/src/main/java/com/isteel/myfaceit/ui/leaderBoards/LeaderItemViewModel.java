package com.isteel.myfaceit.ui.leaderBoards;

import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponsePlayer;

public class LeaderItemViewModel {
    public final ObservableField<String> nickname;
    public final ObservableField<String> avatar;
   /*   public final String game;
    public final String lvl;*/

    public final GameItemViewModelListener mListener;

    private final ResponsePlayer.PlayerByNick mPlayer;

    public LeaderItemViewModel(GameItemViewModelListener mListener, ResponsePlayer.PlayerByNick player) {
        this.mListener = mListener;
        this.mPlayer = player;
        this.avatar = new ObservableField<>("https://corporate.faceit.com/wp-content/uploads/icon-pheasant-preview-2.png");
        this.nickname = new ObservableField<>(mPlayer.getNickName());
    }

    public void onItemClick() {
        mListener.onItemClick();
    }


    public interface GameItemViewModelListener {
        void onItemClick();
    }
}
