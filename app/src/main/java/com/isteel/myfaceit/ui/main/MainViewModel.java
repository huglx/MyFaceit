package com.isteel.myfaceit.ui.main;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel {
    ResponsePlayer.Player player = new ResponsePlayer.Player();

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
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
