package com.isteel.myfaceit.data.remote;

import com.isteel.myfaceit.BuildConfig;

import static com.isteel.myfaceit.BuildConfig.BASE_URL;

public final class ApiEndPoint {

    public static final String ENDPOINT_PLAYERS = BASE_URL + "/search/players";

    public static final String ENDPOINT_RECENT_MATCHES = BASE_URL + "/players/{player_id}/history";

    public static final String ENDPOINT_RECENT_MATCHES_STATS = BASE_URL + "/matches/{match_id}/stats";

    public static final String ENDPOINT_PROFILE = BASE_URL + "/players/{player_id}";

    public static final String ENDPOINT_PROFILE_STATS = BASE_URL + "/players/{player_id}/stats/{game_id}";

    public static final String ENDPOINT_GAMES = BASE_URL + "/games";

    public static final String ENDPOINT_TOP = BASE_URL + "/rankings/games/{game_id}/regions/{region}";


}
