package com.isteel.myfaceit.ui.favourites;

import com.isteel.myfaceit.data.model.ResponseGame.Game;

public class FavouritesItemViewModel {
    public final String label;

    public final GameItemViewModelListener mListener;

    private final Game mGame;

    public FavouritesItemViewModel(GameItemViewModelListener mListener, Game game) {
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
