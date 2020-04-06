package com.isteel.myfaceit.data.remote;

import com.isteel.myfaceit.BuildConfig;

import static com.isteel.myfaceit.BuildConfig.BASE_URL;

public final class ApiEndPoint {
    public static final String ENDPOINT_BLOG = BuildConfig.BASE_URL + "/5926ce9d11000096006ccb30";

    public static final String ENDPOINT_PLAYERS = BASE_URL + "/search/players";

    public static final String ENDPOINT_PROFILE = BASE_URL + "/players";

    public static final String ENDPOINT_GAMES = BASE_URL + "/games";

    public static final String ENDPOINT_TOP = BASE_URL + "/rankings/games/{game_id}/regions/{region}";


}
