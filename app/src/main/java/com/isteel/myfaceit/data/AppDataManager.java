package com.isteel.myfaceit.data;

import android.content.Context;

import com.isteel.myfaceit.data.local.prefs.PreferencesHelper;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.remote.ApiHeader;
import com.isteel.myfaceit.data.remote.ApiHelper;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDataManager implements DataManager{
    private final ApiHelper mApiHelper;

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;


    @Inject
    public AppDataManager(ApiHelper mApiHelper, Context mContext, PreferencesHelper mPreferencesHelper) {
        this.mApiHelper = mApiHelper;
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
    public Single<ResponsePlayer.Player> getPlayerProfile(String query, String game) {
        return mApiHelper.getPlayerProfile(query, game);
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
}
