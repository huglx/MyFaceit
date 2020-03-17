package com.isteel.myfaceit.ui.favourites;

import androidx.annotation.NonNull;
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
import com.isteel.myfaceit.databinding.ActivityPlayerBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.ui.leaderBoards.LeaderActivity;
import com.isteel.myfaceit.ui.players.PlayerActivity;
import com.isteel.myfaceit.utils.BottomNavigationViewHelper;
import com.isteel.myfaceit.utils.LogUtil;

import java.util.List;

import javax.inject.Inject;

public class FavouritesActivity extends BaseActivity<ActivityGameBinding, FavouritesViewModel> implements
FavouritesAdapter.GameAdapterListener, FavouritesNavigator {

    private ActivityGameBinding mainBinding;

    @Inject
    ViewModelProviderFactory factory;
    private FavouritesViewModel mainViewModel;
    @Inject
    FavouritesAdapter mGameAdapter;
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
        return new Intent(context, FavouritesActivity.class);
    }

    @Override
    public FavouritesViewModel getViewModel() {
        mainViewModel = new ViewModelProvider(this,factory).get(FavouritesViewModel.class);
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
                    case R.id.gamesFragment:
                        //    fragment = GamesFragment.newInstance();
                        startActivity(FavouritesActivity.newIntent(this));
                        overridePendingTransition(0, 0);
                        this.finish();


                        break;
                    case R.id.LeaderBoardsFragment:
                        //   fragment = LeaderBoardsFragment.newInstance();
                        startActivity(LeaderActivity.newIntent(this));
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