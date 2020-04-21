package com.isteel.myfaceit.ui.players.profile.recentMaps;

import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.players.NavigatorPlayer;

import java.util.List;

public interface NavigatorMaps {

    void handleError(Throwable throwable);

    void updatePlayer(List<ResponseGame.Segment> segments);

}
