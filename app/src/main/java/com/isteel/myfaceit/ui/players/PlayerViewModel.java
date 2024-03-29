package com.isteel.myfaceit.ui.players;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.model.db.PlayerByNickDB;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;

public class PlayerViewModel extends BaseViewModel<NavigatorPlayer> {
    private MutableLiveData<List<ResponsePlayer.PlayerByNick>> playerListLiveData;

    public PlayerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        playerListLiveData = new MutableLiveData<>();
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


    public void addPlayerToDB(int pos){
        PlayerByNickDB playerByNickDB = new PlayerByNickDB();
        playerByNickDB.nickName = playerListLiveData.getValue().get(pos).getNickName();
        playerByNickDB.playerId = playerListLiveData.getValue().get(pos).getPlayer_id();
        playerByNickDB.avatar = playerListLiveData.getValue().get(pos).getAvater();

       // LogUtil.log(getDataManager().insertPlayer(playerByNickDB)+"123123!@#!@#");

        getCompositeDisposable().add(getDataManager()
                .insertPlayer(playerByNickDB)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responsePlayer -> {
                    LogUtil.log(responsePlayer +" @#!#!");

                }, throwable -> {
                    LogUtil.log(throwable.getMessage()+"@#!#!");
                }));
    }
}
