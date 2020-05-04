package com.isteel.myfaceit.ui.players.profile.mapsStats;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.databinding.MapsFragmentBinding;
import com.isteel.myfaceit.ui.base.BaseFragment;
import com.isteel.myfaceit.ui.players.profile.recentMaps.RecentMapsAdapter;

import java.util.List;

import javax.inject.Inject;

public class MapsFragment extends BaseFragment<MapsFragmentBinding, MapsViewModel> implements NavigatorMaps {

    private MapsViewModel mViewModel;
    MapsFragmentBinding MapsFragmentBinding;
    Boolean isRecentMatches;
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    MapsAdapter mMapsAdapter;
    @Inject
    RecentMapsAdapter mRecentMapsAdapter;
    @Inject
    LinearLayoutManager mLinearLayout;
    @Inject
    LinearLayoutManager mLinearLayout2;

    public static MapsFragment newInstance(String id, Boolean isRecentMatches) {
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putBoolean("isRecentMatches", isRecentMatches);
        MapsFragment MapsFragment = new MapsFragment();
        MapsFragment.setArguments(bundle);
        return MapsFragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.maps_fragment;
    }

    @Override
    public MapsViewModel getViewModel() {
        mViewModel = new ViewModelProvider(this,factory).get(MapsViewModel.class);
        return mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapsFragmentBinding = getViewDataBinding();
        MapsFragmentBinding.recyclerViewAllMatches.setLayoutManager(mLinearLayout);
        MapsFragmentBinding.recyclerViewRecentMatches.setLayoutManager(mLinearLayout2);

        Bundle bundle = this.getArguments();
        assert bundle != null;
        bundle.getString("id", "lox");
        isRecentMatches = bundle.getBoolean("isRecentMatches");

        MapsFragmentBinding.recyclerViewRecentMatches.setAdapter(mRecentMapsAdapter);
        MapsFragmentBinding.recyclerViewAllMatches.setAdapter(mMapsAdapter);
        mViewModel.setIsRecentMatches(isRecentMatches);
        mViewModel.fetchData(bundle.getString("id", ""));
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updatePlayer(List<ResponseGame.Segment> segments) {

    }
}