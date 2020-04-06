package com.isteel.myfaceit.ui.players.profile.recentMaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isteel.myfaceit.R;

public class RecentMapsFragment extends Fragment {

    private RecentMapsViewModel mViewModel;

    public static RecentMapsFragment newInstance() {
        return new RecentMapsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.maps_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      //  mViewModel = ViewModelProviders.of(this).get(BlViewModel.class);
        // TODO: Use the ViewModel
    }

}