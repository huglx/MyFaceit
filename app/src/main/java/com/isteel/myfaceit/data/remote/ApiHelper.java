package com.isteel.myfaceit.data.remote;

import android.util.Log;

import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiHelper implements ApiService{
    private ApiHeader mApiHeader;

    @Inject
    public ApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public Single<ResponsePlayer> getPlayerByNick(String query, String game) {
        Log.d("PLEASE", mApiHeader.getPublicApiHeader().getApiKey());
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PLAYERS)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .addQueryParameter("nickname", query)
                .addQueryParameter("limit", "50")
                .addQueryParameter("game", game)
                .build()
                .getObjectSingle(ResponsePlayer.class);
    }

    @Override
    public Single<ResponseGame> getGames() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GAMES)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .build()
                .getObjectSingle(ResponseGame.class);
    }

    @Override
    public Single<ResponsePlayer> getTop(String game, String region) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_TOP)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .addPathParameter("game_id",game)
                .addPathParameter("region",region)
                .build()
                .getObjectSingle(ResponsePlayer.class);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }
}
