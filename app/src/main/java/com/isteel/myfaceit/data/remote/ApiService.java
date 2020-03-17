package com.isteel.myfaceit.data.remote;

import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;

import io.reactivex.Single;

public interface ApiService {
    Single<ResponsePlayer> getPlayerByNick(String query, String game);

    Single<ResponseGame> getGames();

    Single<ResponsePlayer> getTop(String game, String region);


    ApiHeader getApiHeader();

}
