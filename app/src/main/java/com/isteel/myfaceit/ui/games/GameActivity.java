package com.isteel.myfaceit.ui.games;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.databinding.ActivityGameBinding;
import com.isteel.myfaceit.databinding.ActivityMainBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.ui.leaderBoards.LeaderBoardsFragment;
import com.isteel.myfaceit.ui.main.MainViewModel;
import com.isteel.myfaceit.ui.players.PLayerFragment;
import com.isteel.myfaceit.ui.players.PlayerActivity;
import com.isteel.myfaceit.ui.teams.TeamsFragment;
import com.isteel.myfaceit.utils.BottomNavigationViewHelper;
import com.isteel.myfaceit.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class GameActivity extends BaseActivity<ActivityGameBinding, GamesViewModel> implements
GameAdapter.GameAdapterListener, GameNavigator{

    private ActivityGameBinding mainBinding;

    @Inject
    ViewModelProviderFactory factory;
    private GamesViewModel mainViewModel;
    @Inject
    GameAdapter mGameAdapter;
    @Inject
    LinearLayoutManager mLinearLayout;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_game;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }

    @Override
    public GamesViewModel getViewModel() {
        mainViewModel = new ViewModelProvider(this,factory).get(GamesViewModel.class);
        return mainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = getViewDataBinding();
        setUp();
        mGameAdapter.setListener(this);
        LogUtil.log("dfdf");

        mainViewModel.setNavigator(this);
        Toast.makeText(this, "231321", Toast.LENGTH_SHORT).show();

        mainBinding.recyclerView.setLayoutManager(mLinearLayout);
        mainBinding.recyclerView.setAdapter(mGameAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setUp() {
        setSupportActionBar(mainBinding.toolbar);
        if (getSupportActionBar() != null) {
            mainBinding.toolbar.setTitle("Games");
        }

        BottomNavigationViewHelper.disableShiftMode(mainBinding.bottomNavigation);
        Menu menu = mainBinding.bottomNavigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        mainBinding.bottomNavigation.setOnNavigationItemSelectedListener(item ->{
                switch (item.getItemId()) {
                    case R.id.PlayersFragment:
                        //fragment = PLayerFragment.newInstance();
                        startActivity(PlayerActivity.newIntent(this));
                        overridePendingTransition(0, 0);
                        this.finish();


                        break;
                    case R.id.TeamsFragment:
                        //   fragment = TeamsFragment.newInstance();
                        startActivity(GameActivity.newIntent(this));
                        overridePendingTransition(0, 0);
                        this.finish();


                        break;
                    case R.id.gamesFragment:
                        //    fragment = GamesFragment.newInstance();
                        startActivity(GameActivity.newIntent(this));
                        overridePendingTransition(0, 0);
                        this.finish();


                        break;
                    case R.id.LeaderBoardsFragment:
                        //   fragment = LeaderBoardsFragment.newInstance();
                        startActivity(GameActivity.newIntent(this));
                        overridePendingTransition(0, 0);

                        break;
                }
            return true;
        });

    }

    @Override
    public void onRetryClick() {
        mainViewModel.fetchData();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateBlog(List<ResponseGame.Game> gameList) {
        mGameAdapter.addItems(gameList);
    }
}