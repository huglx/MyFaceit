package com.isteel.myfaceit.ui.players.profile.profileInfo;

import com.isteel.myfaceit.data.model.ResponsePlayer;

import java.util.List;

public interface NavigatorPlayerProfileInfo {

    void handleError(Throwable throwable);

    void updatePlayer(ResponsePlayer.Player player);

}
