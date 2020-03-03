package com.isteel.myfaceit.data.remote;

import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;

import io.reactivex.Single;

public interface ApiService {
    Single<ResponsePlayer> getPlayerByNick(String query);

    Single<ResponseGame> getGames();

    ApiHeader getApiHeader();

}
