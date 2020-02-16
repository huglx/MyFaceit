package com.isteel.myfaceit.ui.teams;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

public class TeamsViewModel extends BaseViewModel {
    public TeamsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public TeamsViewModel() {
    }
}
