package com.isteel.myfaceit.ui.players;

import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;

public class PlayerViewModel extends BaseViewModel<PlayerNavigator> {
    private MutableLiveData<List<ResponsePlayer.Player>> playerListLiveData;

    public PlayerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        playerListLiveData = new MutableLiveData<>();
        fetchData("");
    }

    public void fetchData(String querry) {
        setIsLoading(true);

        LogUtil.log("dfdf");
        getCompositeDisposable().add(getDataManager()
                .getPlayerByNick(querry)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responsePlayer -> {
                    if (responsePlayer != null && responsePlayer.getItems() != null) {
                        playerListLiveData.setValue(responsePlayer.getItems());

                        getNavigator().updatePlayer(playerListLiveData.getValue());
                        setIsLoading(false);

                    }
                }, throwable -> {
                    LogUtil.log(throwable.getMessage());
                    setIsLoading(false);

                }));
    }

    public MutableLiveData<List<ResponsePlayer.Player>> getPlayerListLiveData() {
        return playerListLiveData;
    }
}
