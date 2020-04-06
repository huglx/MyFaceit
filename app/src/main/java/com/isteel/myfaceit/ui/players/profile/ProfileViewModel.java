package com.isteel.myfaceit.ui.players.profile;

import androidx.annotation.NonNull;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

public class ProfileViewModel extends BaseViewModel {
    ResponsePlayer.Player player = new ResponsePlayer.Player();

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
       /* getCompositeDisposable().add(getDataManager()
                .getPlayerByNick("steel")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(PlayerResponse -> {
                    player = PlayerResponse.getItems().get(1);
                    LogUtil.log(PlayerResponse.getItems().get(2).getNickName());
                    }));*/
    }

    @NonNull
    public String getTitle() {
        return player.getNickName()+"sdsd";
    }
}
