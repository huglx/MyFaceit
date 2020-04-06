package com.isteel.myfaceit.ui.players.profile.profileInfo;

import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

public class ProfileInfoViewModel extends BaseViewModel<NavigatorPlayerProfileInfo> {

    public MutableLiveData<String> elo;
    public MutableLiveData<String> maps;
    public MutableLiveData<String> kd;
    public MutableLiveData<String> hs;
    public ProfileInfoViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        elo = new MutableLiveData<>();
    }
    public void fetchData(String querry){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getPlayerProfile(querry,"csgo")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responsePlayer -> {
                    if (responsePlayer != null) {
                        getNavigator().updatePlayer(responsePlayer);
                        elo.setValue( responsePlayer.getGames().getCsgo().getElo());
                    }setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void setElo(String elo) {
        this.elo.setValue(elo);
    }

    public void setMaps(MutableLiveData<String> maps) {
        this.maps = maps;
    }

    public void setKd(MutableLiveData<String> kd) {
        this.kd = kd;
    }

    public void setHs(MutableLiveData<String> hs) {
        this.hs = hs;
    }

    public String getelo() {
        return elo.getValue();
    }

    public String str(){
        return "1233";
    }
    // TODO: Implement the ViewModel
}