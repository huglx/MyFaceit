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
    public MutableLiveData<String> lvl;

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
        lvl = new MutableLiveData<>();
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
                        lvl.setValue(responsePlayer.getGames().getCsgo().getLvl());
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
        return "WinStreak: "+winStreak.getValue();
    }

    public String getMaps() {
        return maps.getValue();
    }

    public String getLvl() {
        return "Faceit level: "+lvl.getValue();
    }

    public String getWinRate() {
        return "Winrate :"+winRate.getValue();
    }

    public String getNickname() {
        return nickName.getValue();
    }

    public String getCountry() {
        return country.getValue();
    }

    public String getInGameNick() {
        return "In-game nickname: " + inGameNick.getValue();
    }

    public String getKD() {
        return "KD: "+ kd.getValue();
    }

    public String getHS() {
        return "HS %: "+hs.getValue();
    }

    public String getAvatar() {
        if (avatar.getValue() == null || avatar.getValue().isEmpty()){
            return "https://corporate.faceit.com/wp-content/uploads/icon-pheasant-preview-2.png";
        }else return avatar.getValue();
    }

    public String getElo() {
        return "elo: " + elo.getValue();
    }

    public String str(){
        return "";
    }
    // TODO: Implement the ViewModel
}