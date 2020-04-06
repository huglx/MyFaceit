package com.isteel.myfaceit;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.ui.favourites.FavouritesViewModel;
import com.isteel.myfaceit.ui.leaderBoards.LeaderBoardsViewModel;
import com.isteel.myfaceit.ui.players.profile.profileInfo.ProfileInfoViewModel;
import com.isteel.myfaceit.ui.players.profile.ProfileViewModel;
import com.isteel.myfaceit.ui.players.PlayerViewModel;
import com.isteel.myfaceit.ui.teams.TeamsViewModel;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

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
    if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
      //noinspection unchecked
      return (T) new ProfileViewModel(dataManager, schedulerProvider);
    }else if (modelClass.isAssignableFrom(FavouritesViewModel.class)) {
      //noinspection unchecked
      return (T) new FavouritesViewModel(dataManager, schedulerProvider);
    }else if (modelClass.isAssignableFrom(PlayerViewModel.class)) {
      //noinspection unchecked
      return (T) new PlayerViewModel(dataManager, schedulerProvider);
    } else if (modelClass.isAssignableFrom(LeaderBoardsViewModel.class)) {
      //noinspection unchecked
      return (T) new LeaderBoardsViewModel(dataManager, schedulerProvider);
    }
      else if (modelClass.isAssignableFrom(ProfileInfoViewModel.class)) {
        //noinspection unchecked
        return (T) new ProfileInfoViewModel(dataManager, schedulerProvider);
    }else if (modelClass.isAssignableFrom(TeamsViewModel.class)) {
      //noinspection unchecked
      return (T) new TeamsViewModel(dataManager, schedulerProvider);
    }else
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}