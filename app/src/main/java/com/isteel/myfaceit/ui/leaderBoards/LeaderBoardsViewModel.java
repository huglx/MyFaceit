package com.isteel.myfaceit.ui.leaderBoards;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

public class LeaderBoardsViewModel extends BaseViewModel {
    public LeaderBoardsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
