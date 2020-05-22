package com.isteel.myfaceit.ui.favourites;

import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponseGame.Game;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.model.db.PlayerByNickDB;

public class FavouritesItemViewModel {
    public final ObservableField<String> nick;
    public final ObservableField<String> avatar;

    public final GameItemViewModelListener mListener;

    private final PlayerByNickDB mPlayer;

    public FavouritesItemViewModel(GameItemViewModelListener mListener, PlayerByNickDB player) {
        this.mListener = mListener;
        this.mPlayer = player;
        this.nick = new ObservableField<>(mPlayer.nickName);
        if(mPlayer.avatar== null || mPlayer.avatar.isEmpty()){
            this.avatar = new ObservableField<>("https://corporate.faceit.com/wp-content/uploads/icon-pheasant-preview-2.png");
        } else this.avatar =new ObservableField<>(mPlayer.avatar);

    }

    public void onItemClick() {
        mListener.onItemClick(mPlayer.playerId);
    }


    public interface GameItemViewModelListener {
        void onItemClick(String id);
    }
}
