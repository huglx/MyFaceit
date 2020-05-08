package com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponseMatch;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.ui.players.NavigatorPlayer;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.Objects;

public class RecentMapsStatsViewModel extends BaseViewModel<NavigatorMapsStats> {
    public MutableLiveData<List<ResponseMatch.Rounds>> matchListLiveData;
    public MutableLiveData<String> score;


    public RecentMapsStatsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        matchListLiveData = new MutableLiveData<>();
        score = new MutableLiveData<>();
    }

    public void fetchData(String id) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getRecentMatchesStats(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(responseMatch -> {
                    if (responseMatch != null) {
                        matchListLiveData.setValue(responseMatch.getRoundsList());
                        score.setValue(responseMatch.getRoundsList().get(0).getRoundStats().getScore());
                        LogUtil.log(responseMatch.getRoundsList().get(0).getRoundStats().getScore() + "1!@#!2");
                        getNavigator().updatePlayer1(responseMatch.getRoundsList().get(0).getTeams().get(0).getPlayers());
                        getNavigator().updatePlayer2(responseMatch.getRoundsList().get(0).getTeams().get(1).getPlayers());

                    }setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    LogUtil.log(throwable.getMessage());
                }));
    }

    public List<ResponsePlayer.Player> getFirstTeam() {
        return Objects.requireNonNull(matchListLiveData.getValue()).get(0).getTeams().get(0).getPlayers();
    }

    public String getScore() {
        return score.getValue();
    }

    public List<ResponsePlayer.Player> getSecondTeam() {
        return Objects.requireNonNull(matchListLiveData.getValue()).get(0).getTeams().get(1).getPlayers();

    }

}
