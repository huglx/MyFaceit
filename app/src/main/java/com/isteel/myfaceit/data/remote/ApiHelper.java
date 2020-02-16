package com.isteel.myfaceit.data.remote;

import android.util.Log;

import com.isteel.myfaceit.data.model.BlogResponse;
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
    public Single<ResponsePlayer> getPlayerByNick(String query) {
        Log.d("PLEASE", mApiHeader.getPublicApiHeader().getApiKey());
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PLAYERS)
                .addHeaders("Authorization","Bearer " + mApiHeader.getPublicApiHeader().getApiKey().trim())
                .addQueryParameter("nickname", query)
                .build()
                .getObjectSingle(ResponsePlayer.class);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }
}