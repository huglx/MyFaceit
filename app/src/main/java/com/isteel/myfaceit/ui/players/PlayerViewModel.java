package com.isteel.myfaceit.ui.players;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;

public class PlayerViewModel extends BaseViewModel<NavigatorPlayer> {
    private MutableLiveData<List<ResponsePlayer.PlayerByNick>> playerListLiveData;

    public PlayerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        playerListLiveData = new MutableLiveData<>();
        fetchData("skyline4132");
    }

    public void fetchData(String querry) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getPlayerByNick(querry, "csgo")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responsePlayer -> {
                    if (responsePlayer != null && responsePlayer.getItems() != null) {
                        getNavigator().updatePlayer(responsePlayer.getItems());
                        playerListLiveData.setValue(responsePlayer.getItems());
                    }setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }

    public LiveData<List<ResponsePlayer.PlayerByNick>> getPlayerListLiveData() {
        return playerListLiveData;
    }
}
