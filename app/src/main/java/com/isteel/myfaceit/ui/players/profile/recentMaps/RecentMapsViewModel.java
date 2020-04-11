package com.isteel.myfaceit.ui.players.profile.recentMaps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import java.util.List;

public class RecentMapsViewModel extends BaseViewModel {
    private MutableLiveData<List<ResponseGame.Segment>> mapsList;

    public RecentMapsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        mapsList = new MutableLiveData<>();
    }

    public void fetchData(String id){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getStats(id, "csgo")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(segments -> {
                    if (segments != null) {
                        mapsList.setValue(segments.getSegmentList());
                    }setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public LiveData<List<ResponseGame.Segment>> getMapsList() {
        return mapsList;
    }
}