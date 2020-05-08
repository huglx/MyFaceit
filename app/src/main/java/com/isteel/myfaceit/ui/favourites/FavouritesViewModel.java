package com.isteel.myfaceit.ui.favourites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.db.PlayerByNickDB;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;

public class FavouritesViewModel extends BaseViewModel<FavouritesNavigator> {
    private MutableLiveData<List<PlayerByNickDB>> playerListLiveData;

    public FavouritesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        playerListLiveData = new MutableLiveData<>();
        fetchData();

    }

    public void fetchData() {
        getCompositeDisposable().add(getDataManager()
                .getAllPlayers()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responsePlayer -> {
                    playerListLiveData.setValue(responsePlayer);
                 //   getNavigator().updatePlayers(responsePlayer);
                }, throwable -> {
                    LogUtil.log(throwable.getMessage()+"@#!#!");
                }));
    }

    public LiveData<List<PlayerByNickDB>> getPlayerListLiveData() {
        return playerListLiveData;
    }
}
