package com.isteel.myfaceit.ui.favourites;

import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponseGame.Game;
import com.isteel.myfaceit.data.model.ResponsePlayer;

public class FavouritesItemViewModel {
    public final ObservableField<String> nick;

    public final GameItemViewModelListener mListener;

    private final ResponsePlayer.PlayerByNick mPlayer;

    public FavouritesItemViewModel(GameItemViewModelListener mListener, ResponsePlayer.PlayerByNick player) {
        this.mListener = mListener;
        this.mPlayer = player;
        this.nick = new ObservableField<>(mPlayer.getNickName());
    }

    public void onItemClick() {
        mListener.onItemClick();
    }


    public interface GameItemViewModelListener {
        void onItemClick();
    }
}
