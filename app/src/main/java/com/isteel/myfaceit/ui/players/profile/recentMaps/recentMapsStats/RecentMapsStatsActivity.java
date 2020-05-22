package com.isteel.myfaceit.ui.players.profile.recentMaps.recentMapsStats;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.ActivityRecentMapsStatsBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.ui.players.NavigatorPlayer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RecentMapsStatsActivity extends BaseActivity<ActivityRecentMapsStatsBinding, RecentMapsStatsViewModel> implements HasSupportFragmentInjector
, NavigatorMapsStats {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    private ActivityRecentMapsStatsBinding mainBinding;
    @Inject
    ViewModelProviderFactory factory;

    RecentMapsStatsAdapter recentMapsStatsAdapter;
    RecentMapsStatsAdapter recentMapsStatsAdapter2;

    LinearLayoutManager linearLayout;
    LinearLayoutManager linearLayout2;


    private RecentMapsStatsViewModel viewModel;
    String id;

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, RecentMapsStatsActivity.class);
        intent.putExtra("id", id);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recent_maps_stats;
    }

    @Override
    public RecentMapsStatsViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(RecentMapsStatsViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = getViewDataBinding();
        recentMapsStatsAdapter = new RecentMapsStatsAdapter(new ArrayList<>());
        recentMapsStatsAdapter2 = new RecentMapsStatsAdapter(new ArrayList<>());

        linearLayout = new LinearLayoutManager(this);
        linearLayout2 = new LinearLayoutManager(this);
        viewModel.setNavigator(this);


        mainBinding.firstTeam.setLayoutManager(linearLayout);
        mainBinding.firstTeam.setAdapter(recentMapsStatsAdapter);

        mainBinding.secondTeam.setLayoutManager(linearLayout2);
        mainBinding.secondTeam.setAdapter(recentMapsStatsAdapter2);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        viewModel.fetchData(id);
        setUp();
    }

    private void setUp() {
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updatePlayer1(List<ResponsePlayer.Player> playersList) {
        recentMapsStatsAdapter.addItems(playersList);
    }

    @Override
    public void updatePlayer2(List<ResponsePlayer.Player> playersList) {
        recentMapsStatsAdapter2.addItems(playersList);

    }

    @Override
    public void test() {

    }
}
