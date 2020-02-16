package com.isteel.myfaceit.data;

import android.content.Context;

import com.isteel.myfaceit.data.model.BlogResponse;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.remote.ApiHeader;
import com.isteel.myfaceit.data.remote.ApiHelper;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDataManager implements DataManager{
    private final ApiHelper mApiHelper;

    private final Context mContext;

    @Inject
    public AppDataManager(ApiHelper mApiHelper, Context mContext) {
        this.mApiHelper = mApiHelper;
        this.mContext = mContext;
    }

    @Override
    public Single<ResponsePlayer> getPlayerByNick(String query) {
        return mApiHelper.getPlayerByNick(query);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }
}
