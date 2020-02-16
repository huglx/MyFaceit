package com.isteel.myfaceit.ui.teams;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.databinding.TeamsFragmentBinding;
import com.isteel.myfaceit.ui.base.BaseFragment;

import javax.inject.Inject;

public class TeamsFragment extends BaseFragment<TeamsFragmentBinding, TeamsViewModel> {
    TeamsFragmentBinding teamsFragmentBinding;
    private TeamsViewModel teamsViewModel;
    private Context context;

    @Inject
    ViewModelProviderFactory factory;

    public static TeamsFragment newInstance() {
        Bundle args = new Bundle();
        TeamsFragment fragment = new TeamsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.TeamsViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.teams_fragment;
    }

    @Override
    public TeamsViewModel getViewModel() {
        teamsViewModel = ViewModelProviders.of(this,factory).get(TeamsViewModel.class);
        return teamsViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        teamsFragmentBinding = getViewDataBinding();
    }
}