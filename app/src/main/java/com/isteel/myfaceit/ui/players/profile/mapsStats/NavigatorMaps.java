package com.isteel.myfaceit.ui.players.profile.mapsStats;

import com.isteel.myfaceit.data.model.ResponseGame;

import java.util.List;

public interface NavigatorMaps {

    void handleError(Throwable throwable);

    void updatePlayer(List<ResponseGame.Segment> segments);

}
