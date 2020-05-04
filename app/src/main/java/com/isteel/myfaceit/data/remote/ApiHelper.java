package com.isteel.myfaceit.data.remote;

import android.util.Log;

import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponseMatch;
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
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PLAYERS)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .addQueryParameter("nickname", query)
                .addQueryParameter("limit", "50")
                .addQueryParameter("game", game)
                .build()
                .getObjectSingle(ResponsePlayer.class);
    }

    @Override
    public Single<ResponsePlayer.Player> getPlayerProfile(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PROFILE)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .addPathParameter("player_id", id)
                .build()
                .getObjectSingle(ResponsePlayer.Player.class);
    }

    @Override
    public Single<ResponseGame> getGames() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GAMES)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .build()
                .getObjectSingle(ResponseGame.class);
    }

    @Override
    public Single<ResponseMatch> getRecentMatches(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_RECENT_MATCHES)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .addPathParameter("player_id", id)
                .addQueryParameter("game", "csgo")
                .build()
                .getObjectSingle(ResponseMatch.class);
    }

    @Override
    public Single<ResponseMatch> getRecentMatchesStats(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_RECENT_MATCHES_STATS)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .addPathParameter("match_id", id)
                .build()
                .getObjectSingle(ResponseMatch.class);
    }

    @Override
    public Single<ResponseGame.Csgo> getStats(String player_id, String game) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PROFILE_STATS)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .addPathParameter("player_id", player_id)
                .addPathParameter("game_id", game)
                .build()
                .getObjectSingle(ResponseGame.Csgo.class);
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
