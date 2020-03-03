package com.isteel.myfaceit.ui.games;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;

public class GamesViewModel extends BaseViewModel<GameNavigator> {
    private MutableLiveData<List<ResponseGame.Game>> gameListLiveData;

    public GamesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        gameListLiveData = new MutableLiveData<>();
        fetchData();

    }

    public void fetchData() {
        setIsLoading(true);

        LogUtil.log("dfdf");
        getCompositeDisposable().add(getDataManager()
                .getGames()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responseGame -> {
                    if (responseGame != null && responseGame.getItems() != null) {
                        LogUtil.log(responseGame.getItems().get(0).getLabel()+"1234");
                        gameListLiveData.setValue(responseGame.getItems());
                        getNavigator().updateBlog(gameListLiveData.getValue());
                        LogUtil.log(gameListLiveData.getValue().size()+"AAAA");
                        setIsLoading(false);

                    }
                }, throwable -> {
                    LogUtil.log(throwable.getMessage());
                    setIsLoading(false);

                }));
    }

    public LiveData<List<ResponseGame.Game>> getGameListLiveData() {
        return gameListLiveData;
    }
}
