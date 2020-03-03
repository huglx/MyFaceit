package com.isteel.myfaceit.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.ActivityMainBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.ui.games.GameActivity;
import com.isteel.myfaceit.ui.games.GamesFragment;
import com.isteel.myfaceit.ui.games.GamesViewModel;
import com.isteel.myfaceit.ui.leaderBoards.LeaderBoardsFragment;
import com.isteel.myfaceit.ui.players.PLayerFragment;
import com.isteel.myfaceit.ui.players.PlayerActivity;
import com.isteel.myfaceit.ui.players.PlayerViewModel;
import com.isteel.myfaceit.ui.teams.TeamsFragment;
import com.isteel.myfaceit.utils.BottomNavigationViewHelper;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements HasSupportFragmentInjector {

    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    private ActivityMainBinding mainBinding;

    @Inject
    ViewModelProviderFactory factory;
    private MainViewModel mainViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mainViewModel = new ViewModelProvider(this,factory).get(MainViewModel.class);
        return mainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = getViewDataBinding();
        setUp();
         startActivity(GameActivity.newIntent(this));
       // loadFragment(GamesFragment.newInstance());
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_container, fragment)
                    .commit();
            return true;
        }else return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    private void setUp() {
        BottomNavigationViewHelper.disableShiftMode(mainBinding.bottomNavigation);
        Menu menu = mainBinding.bottomNavigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        mainBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.PlayersFragment:
                    //fragment = PLayerFragment.newInstance();
                    startActivity(PlayerActivity.newIntent(this));
                    MainActivity.this.overridePendingTransition(0, 0);
                    this.finish();

                    break;
                case R.id.TeamsFragment:
                 //   fragment = TeamsFragment.newInstance();
                   startActivity(GameActivity.newIntent(this));
                    MainActivity.this.overridePendingTransition(0, 0);
                    this.finish();

                    break;
                case R.id.gamesFragment:
                //    fragment = GamesFragment.newInstance();
                    startActivity(GameActivity.newIntent(this));
                    MainActivity.this.overridePendingTransition(0, 0);
                    this.finish();

                    break;
                case R.id.LeaderBoardsFragment:
                 //   fragment = LeaderBoardsFragment.newInstance();
                    startActivity(GameActivity.newIntent(this));
                    MainActivity.this.overridePendingTransition(0, 0);
                    this.finish();

                    break;
            }
            return loadFragment(fragment);
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