package com.isteel.myfaceit.ui.players.profile;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.tabs.TabLayout;
import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.databinding.ActivityProfileBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.utils.LogUtil;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding, ProfileViewModel> implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    private ActivityProfileBinding mainBinding;

    @Inject
    ProfilePagerAdapter mPagerAdapter;

    @Inject
    ViewModelProviderFactory factory;
    private ProfileViewModel profileViewModel;
    String id1;

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("id", id);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    public ProfileViewModel getViewModel() {
        profileViewModel = new ViewModelProvider(this,factory).get(ProfileViewModel.class);
        return profileViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = getViewDataBinding();

        Intent intent = getIntent();

        id1 = intent.getStringExtra("id");
        setUp();
    }

    private void setUp() {
        mPagerAdapter.setCount(2);
        mPagerAdapter.setIdForProfileFragments(id1);
        mainBinding.feedViewPager.setAdapter(mPagerAdapter);


        mainBinding.tabLayout.addTab(mainBinding.tabLayout.newTab().setText("1"));
        mainBinding.tabLayout.addTab(mainBinding.tabLayout.newTab().setText("2"));

        mainBinding.feedViewPager.setOffscreenPageLimit(mainBinding.tabLayout.getTabCount());
        mainBinding.feedViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainBinding.tabLayout));

        mainBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainBinding.feedViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}