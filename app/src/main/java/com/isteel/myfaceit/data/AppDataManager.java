package com.isteel.myfaceit.data;

import android.content.Context;

import com.isteel.myfaceit.data.local.datebase.DbHelper;
import com.isteel.myfaceit.data.local.prefs.PreferencesHelper;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponseMatch;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.model.db.PlayerByNickDB;
import com.isteel.myfaceit.data.remote.ApiHeader;
import com.isteel.myfaceit.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AppDataManager implements DataManager{
    private final ApiHelper mApiHelper;

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final DbHelper mDbHelper;


    @Inject
    public AppDataManager(ApiHelper mApiHelper,DbHelper dbHelper, Context mContext, PreferencesHelper mPreferencesHelper) {
        this.mApiHelper = mApiHelper;
        this.mDbHelper = dbHelper;
        this.mContext = mContext;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    @Override
    public Single<ResponsePlayer> getPlayerByNick(String query, String game) {
        return mApiHelper.getPlayerByNick(query, game);
    }

    @Override
    public Single<ResponseGame> getGames() {
        return mApiHelper.getGames();
    }

    @Override
    public Single<ResponseMatch> getRecentMatches(String id) {
        return mApiHelper.getRecentMatches(id);
    }

    @Override
    public Single<ResponseMatch> getRecentMatchesStats(String id) {
        return mApiHelper.getRecentMatchesStats(id);
    }

    @Override
    public Single<ResponseGame.Csgo> getStats(String player_id, String game) {
        return mApiHelper.getStats(player_id, game);
    }

    @Override
    public Single<ResponsePlayer.Player> getPlayerProfile(String id) {
        return mApiHelper.getPlayerProfile(id);
    }

    @Override
    public Single<ResponsePlayer> getTop(String game, String region) {
        return mApiHelper.getTop(game,region);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public String getGame() {
        return mPreferencesHelper.getGame();
    }

    @Override
    public void setGame(String game) {
        mPreferencesHelper.setGame(game);
    }

    @Override
    public List<ResponsePlayer.PlayerByNick> getProfile() {
        return mPreferencesHelper.getProfile();
    }
    @Override
    public void setProfile(ResponsePlayer.PlayerByNick profile) {
        mPreferencesHelper.setProfile(profile);
    }

    @Override
    public Observable<List<PlayerByNickDB>> getAllPlayers() {
        return mDbHelper.getAllPlayers();
    }

    @Override
    public Observable<Boolean> insertPlayer(PlayerByNickDB playerByNickDB) {
        return mDbHelper.insertPlayer(playerByNickDB);
    }
}
