package com.isteel.myfaceit.ui.players.profile;

import androidx.annotation.NonNull;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

public class ProfileViewModel extends BaseViewModel {

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }
}
