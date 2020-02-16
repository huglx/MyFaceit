package com.isteel.myfaceit.data.remote;

import com.isteel.myfaceit.data.model.BlogResponse;
import com.isteel.myfaceit.data.model.ResponsePlayer;

import io.reactivex.Single;

public interface ApiService {
    Single<ResponsePlayer> getPlayerByNick(String query);

    ApiHeader getApiHeader();

}
