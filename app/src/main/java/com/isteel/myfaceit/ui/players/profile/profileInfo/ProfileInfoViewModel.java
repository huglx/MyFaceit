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
    public MutableLiveData<String> nickName;
    public MutableLiveData<String> avatar;
    public MutableLiveData<String> winStreak;
    public MutableLiveData<String> country;
    public MutableLiveData<String> inGameNick;
    public MutableLiveData<String> winRate;

    public ProfileInfoViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        elo = new MutableLiveData<>();
        nickName = new MutableLiveData<>();
        kd = new MutableLiveData<>();
        hs = new MutableLiveData<>();
        maps = new MutableLiveData<>();
        winStreak = new MutableLiveData<>();
        winRate = new MutableLiveData<>();
        country = new MutableLiveData<>();
        inGameNick = new MutableLiveData<>();
        avatar = new MutableLiveData<>();
    }
    public void fetchData(String id){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getPlayerProfile(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responsePlayer -> {
                    if (responsePlayer != null) {
                        elo.setValue( responsePlayer.getGames().getCsgo().getElo());
                        nickName.setValue(responsePlayer.getNickName());
                        avatar.setValue(responsePlayer.getAvatar());
                        country.setValue(responsePlayer.getCountry());
                        inGameNick.setValue(responsePlayer.getGames().getCsgo().getGame_player_name());
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));

        getCompositeDisposable().add(getDataManager()
                .getStats(id, "csgo")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(csgo -> {
                    if (csgo != null) {
                        kd.setValue(csgo.getLifetime().getKd());
                        hs.setValue(csgo.getLifetime().getHs());
                        maps.setValue(csgo.getLifetime().getMaps());
                        winStreak.setValue(csgo.getLifetime().getLongestWinStreak());
                        winRate.setValue(csgo.getLifetime().getWinRate());
                    }setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                }));
    }
    public String getWinStreak() {
        return winStreak.getValue();
    }

    public String getMaps() {
        return maps.getValue();
    }

    public String getWinRate() {
        return winRate.getValue();
    }

    public String getNickname() {
        return nickName.getValue();
    }

    public String getCountry() {
        return country.getValue();
    }

    public String getInGameNick() {
        return inGameNick.getValue();
    }

    public String getKD() {
        return kd.getValue();
    }

    public String getHS() {
        return hs.getValue();
    }

    public String getAvatar() {
        return avatar.getValue();
    }

    public String getElo() {
        return elo.getValue();
    }

    public String str(){
        return "1233";
    }
    // TODO: Implement the ViewModel
}