package com.isteel.myfaceit.ui.leaderBoards;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.databinding.LeaderFragmentBinding;
import com.isteel.myfaceit.databinding.LeaderFragmentBinding;
import com.isteel.myfaceit.ui.base.BaseFragment;

import javax.inject.Inject;

public class LeaderBoardsFragment extends BaseFragment<LeaderFragmentBinding, LeaderBoardsViewModel> {
    LeaderFragmentBinding leaderBoardsBinding;
    private LeaderBoardsViewModel leaderBoardsViewModel;
    private Context context;

    @Inject
    ViewModelProviderFactory factory;

    public static LeaderBoardsFragment newInstance() {
        Bundle args = new Bundle();
        LeaderBoardsFragment fragment = new LeaderBoardsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.leader_fragment;
    }

    @Override
    public LeaderBoardsViewModel getViewModel() {
        leaderBoardsViewModel = new ViewModelProvider(this,factory).get(LeaderBoardsViewModel.class);
        return leaderBoardsViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        leaderBoardsBinding = getViewDataBinding();
    }
}