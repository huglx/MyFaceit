package com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats;

import com.isteel.myfaceit.data.model.ResponsePlayer;

import java.util.List;

public interface NavigatorMapsStats {

    void handleError(Throwable throwable);

    void updatePlayer1(List<ResponsePlayer.Player> playersList);

    void updatePlayer2(List<ResponsePlayer.Player> playersList);

    void test();
}
