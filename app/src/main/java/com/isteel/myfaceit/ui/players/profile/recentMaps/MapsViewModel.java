package com.isteel.myfaceit.ui.players.profile.recentMaps;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.data.model.ResponseMatch;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class MapsViewModel extends BaseViewModel<NavigatorMaps> {
    private MutableLiveData<List<ResponseGame.Segment>> mapsList;
    private MutableLiveData<List<ResponseMatch.Match>> matchList;
    List<String> strings;

    private Boolean isRecentMatches;

    public MapsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        mapsList = new MutableLiveData<>();
        matchList = new MutableLiveData<>();
    }

    public void fetchData(String id){
            setIsLoading(true);

            if(isRecentMatches) {
                getCompositeDisposable().add(getDataManager()
                        .getRecentMatches(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(match -> {
                            if (match != null) {
                                matchList.setValue(match.getMatchList());
                            }
                            setIsLoading(false);
                        }, throwable -> {
                            setIsLoading(false);

                        }));
            }else {
                getCompositeDisposable().add(getDataManager()
                        .getStats(id,"csgo")
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(match -> {
                            if (match != null) {
                            mapsList.setValue(match.getSegmentList());
                            }
                            setIsLoading(false);
                        }, throwable -> {
                            setIsLoading(false);

                        }));
            }
    }

    public Boolean getIsRecentMatches() {
        return isRecentMatches;
    }

    public void setIsRecentMatches(Boolean recentMatches) {
        isRecentMatches = recentMatches;
    }

    public LiveData<List<ResponseGame.Segment>> getMapsList() {
        return mapsList;
    }

    public LiveData<List<ResponseMatch.Match>> getMatchList() {
        return matchList;
    }
}