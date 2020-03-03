package com.isteel.myfaceit.ui.players;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.databinding.PlayerFragmentBinding;
import com.isteel.myfaceit.ui.base.BaseFragment;
import com.isteel.myfaceit.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class PLayerFragment extends BaseFragment<PlayerFragmentBinding, PlayerViewModel> {
    PlayerFragmentBinding playerFragmentBinding;
    private PlayerViewModel playerViewModel;
    private Context context;

    ViewModelProviderFactory factory;
    @Inject
    DataManager dataManager;
    @Inject
    SchedulerProvider schedulerProvider;

    public static PLayerFragment newInstance() {
        Bundle args = new Bundle();
        PLayerFragment fragment = new PLayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.player_fragment;
    }

    @Override
    public PlayerViewModel getViewModel() {
        factory = new ViewModelProviderFactory(dataManager,schedulerProvider);
        playerViewModel = new ViewModelProvider(this,factory).get(PlayerViewModel.class);
        return playerViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playerFragmentBinding = getViewDataBinding();

    }
}