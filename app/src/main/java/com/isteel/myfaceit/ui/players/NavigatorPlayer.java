package com.isteel.myfaceit.ui.players;

import com.isteel.myfaceit.data.model.ResponsePlayer;

import java.util.List;

public interface NavigatorPlayer {

    void handleError(Throwable throwable);

    void updatePlayer(List<ResponsePlayer.PlayerByNick> gameList);

    void test();
}
