package com.isteel.myfaceit.data.remote;

import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponseMatch;
import com.isteel.myfaceit.data.model.ResponsePlayer;

import io.reactivex.Single;

public interface ApiService {
    Single<ResponsePlayer> getPlayerByNick(String query, String game);

    Single<ResponseGame> getGames();

    Single<ResponseMatch> getRecentMatches(String id);

    Single<ResponseMatch> getRecentMatchesStats(String id);

    Single<ResponseGame.Csgo> getStats(String player_id, String game);

    Single<ResponsePlayer.Player> getPlayerProfile(String id);

    Single<ResponsePlayer> getTop(String game, String region);

    ApiHeader getApiHeader();

}
