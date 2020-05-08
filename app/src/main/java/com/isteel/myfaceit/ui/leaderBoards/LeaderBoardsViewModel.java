package com.isteel.myfaceit.ui.leaderBoards;

import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;

public class LeaderBoardsViewModel extends BaseViewModel<LeaderNavigator> {

    private MutableLiveData<List<ResponsePlayer.PlayerByNick>> playerListLiveData;

    public LeaderBoardsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        playerListLiveData = new MutableLiveData<>();
        fetchData("", "EU");
    }

    public void fetchData(String game,String region) {
        setIsLoading(true);
        getDataManager().setGame("csgo");
        getCompositeDisposable().add(getDataManager()
                .getTop("csgo", "EU")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responsePlayer -> {
                    if (responsePlayer != null && responsePlayer.getItems() != null) {
                        playerListLiveData.setValue(responsePlayer.getItems());
                        setIsLoading(false);

                    }
                }, throwable -> {
                    LogUtil.log(throwable.getMessage());
                    setIsLoading(false);

                }));
    }

    public MutableLiveData<List<ResponsePlayer.PlayerByNick>> getPlayerListLiveData() {
        return playerListLiveData;
    }
}
