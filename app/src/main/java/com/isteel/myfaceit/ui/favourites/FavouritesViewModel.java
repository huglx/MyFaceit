package com.isteel.myfaceit.ui.favourites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;

public class FavouritesViewModel extends BaseViewModel<FavouritesNavigator> {
    private MutableLiveData<List<ResponsePlayer.PlayerByNick>> playerListLiveData;

    public FavouritesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        playerListLiveData = new MutableLiveData<>();
        fetchData();

    }

    public void fetchData() {
        playerListLiveData.setValue(getDataManager()
        .getProfile());
    }

    public LiveData<List<ResponsePlayer.PlayerByNick>> getPlayerListLiveData() {
        return playerListLiveData;
    }
}
