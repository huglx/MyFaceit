package com.isteel.myfaceit.ui.players.profile.profileInfo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.ProfileInfoFragmentBinding;
import com.isteel.myfaceit.ui.base.BaseFragment;
import com.isteel.myfaceit.utils.LogUtil;

import java.util.List;

import javax.inject.Inject;

public class ProfileInfoFragment extends BaseFragment<ProfileInfoFragmentBinding, ProfileInfoViewModel>
        implements NavigatorPlayerProfileInfo{

    private ProfileInfoFragmentBinding PersonalInfoFragmentBinding;
    private ProfileInfoViewModel mViewModel;
    @Inject
    ViewModelProviderFactory factory;

    private String id;

    public static ProfileInfoFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("id",str);
        ProfileInfoFragment profileInfoFragment = new ProfileInfoFragment();
        profileInfoFragment.setArguments(bundle);
        return profileInfoFragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.profile_info_fragment;
    }

    @Override
    public ProfileInfoViewModel getViewModel() {
        Bundle bundle = this.getArguments();
        assert bundle != null;
        bundle.getString("id", "lox");
        LogUtil.log(bundle.getString("id"));
        mViewModel = new ViewModelProvider(this,factory).get(ProfileInfoViewModel.class);
        mViewModel.fetchData(bundle.getString("id", ""));
        return mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PersonalInfoFragmentBinding = getViewDataBinding();

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updatePlayer(ResponsePlayer.Player player) {

    }
}