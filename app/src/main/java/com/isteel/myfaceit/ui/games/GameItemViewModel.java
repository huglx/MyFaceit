package com.isteel.myfaceit.ui.games;

import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponseGame.Game;

public class GameItemViewModel {
    public final String label;

    public final GameItemViewModelListener mListener;

    private final Game mGame;

    public GameItemViewModel(GameItemViewModelListener mListener, Game game) {
        this.mListener = mListener;
        this.mGame = game;
        this.label = mGame.getLabel();
    }

    public void onItemClick() {
        mListener.onItemClick();
    }


    public interface GameItemViewModelListener {
        void onItemClick();
    }
}
