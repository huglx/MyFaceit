package com.isteel.myfaceit.ui.favourites;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.databinding.GamesFragmentBinding;
import com.isteel.myfaceit.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class GamesFragment extends BaseFragment<GamesFragmentBinding, FavouritesViewModel> implements
            FavouritesAdapter.GameAdapterListener, FavouritesNavigator, HasSupportFragmentInjector {

    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    GamesFragmentBinding gamesFragmentBinding;
    private FavouritesViewModel favouritesViewModel;

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    FavouritesAdapter mGameAdapter;

    LinearLayoutManager mLinearLayout = new LinearLayoutManager(getContext());

    public static GamesFragment newInstance() {
        Bundle args = new Bundle();
        GamesFragment fragment = new GamesFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.games_fragment;
    }

    @Override
    public FavouritesViewModel getViewModel() {
       // factory = new ViewModelProviderFactory(dataManager, schedulerProvider);
        favouritesViewModel = new ViewModelProvider(this,factory).get(FavouritesViewModel.class);
        return favouritesViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        favouritesViewModel.setNavigator(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gamesFragmentBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        gamesFragmentBinding.recyclerView.setLayoutManager(mLinearLayout);
        gamesFragmentBinding.recyclerView.setAdapter(mGameAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        gamesFragmentBinding.searchView.setMenuItem(item);
    }

    @Override
    public void onRetryClick() {
        favouritesViewModel.fetchData();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateBlog(List<ResponseGame.Game> gameList) {
        mGameAdapter.addItems(gameList);
    }
}