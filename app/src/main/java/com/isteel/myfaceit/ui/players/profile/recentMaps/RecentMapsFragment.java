package com.isteel.myfaceit.ui.players.profile.recentMaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.databinding.MapsFragmentBinding;
import com.isteel.myfaceit.ui.base.BaseFragment;
import com.isteel.myfaceit.ui.base.BaseViewModel;
import com.isteel.myfaceit.ui.players.PlayerAdapter;
import com.isteel.myfaceit.ui.players.profile.profileInfo.ProfileInfoFragment;
import com.isteel.myfaceit.ui.players.profile.profileInfo.ProfileInfoViewModel;

import javax.inject.Inject;

public class RecentMapsFragment extends BaseFragment<MapsFragmentBinding, RecentMapsViewModel> {

    private RecentMapsViewModel mViewModel;
    MapsFragmentBinding MapsFragmentBinding;
    @Inject
    ViewModelProviderFactory factory;

    @Inject
    MapsAdapter mMapsAdapter;
    @Inject
    LinearLayoutManager mLinearLayout;

    public static RecentMapsFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("id",str);
        RecentMapsFragment  MapsFragment = new RecentMapsFragment();
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
    public RecentMapsViewModel getViewModel() {
        mViewModel = new ViewModelProvider(this,factory).get(RecentMapsViewModel.class);
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
        MapsFragmentBinding.recyclerView.setLayoutManager(mLinearLayout);
        MapsFragmentBinding.recyclerView.setAdapter(mMapsAdapter);

        Bundle bundle = this.getArguments();
        assert bundle != null;
        bundle.getString("id", "lox");

        mViewModel.fetchData(bundle.getString("id", ""));


    }


}