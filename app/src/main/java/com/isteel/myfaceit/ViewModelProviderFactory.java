package com.isteel.myfaceit;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.ui.main.MainViewModel;
import com.isteel.myfaceit.ui.players.PlayerViewModel;
import com.isteel.myfaceit.ui.teams.TeamsViewModel;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

  private final DataManager dataManager;
  private final SchedulerProvider schedulerProvider;

  @Inject
  public ViewModelProviderFactory(DataManager dataManager,
                                  SchedulerProvider schedulerProvider) {
    this.dataManager = dataManager;
    this.schedulerProvider = schedulerProvider;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(MainViewModel.class)) {
      //noinspection unchecked
      return (T) new MainViewModel(dataManager, schedulerProvider);
    }else if (modelClass.isAssignableFrom(PlayerViewModel.class)) {
      //noinspection unchecked
      return (T) new PlayerViewModel(dataManager, schedulerProvider);
    }else if (modelClass.isAssignableFrom(TeamsViewModel.class)) {
      //noinspection unchecked
      return (T) new TeamsViewModel(dataManager, schedulerProvider);
    }else
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}