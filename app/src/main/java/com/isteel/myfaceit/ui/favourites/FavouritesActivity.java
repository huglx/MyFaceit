package com.isteel.myfaceit.ui.favourites;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.db.PlayerByNickDB;
import com.isteel.myfaceit.databinding.ActivityFavouritesBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.ui.leaderBoards.LeaderActivity;
import com.isteel.myfaceit.ui.players.PlayerActivity;
import com.isteel.myfaceit.utils.BottomNavigationViewHelper;
import com.isteel.myfaceit.utils.SwipeController;
import com.isteel.myfaceit.utils.SwipeControllerActions;

import java.util.List;

import javax.inject.Inject;

public class FavouritesActivity extends BaseActivity<ActivityFavouritesBinding, FavouritesViewModel> implements
FavouritesAdapter.GameAdapterListener, FavouritesNavigator {

    private ActivityFavouritesBinding mainBinding;

    @Inject
    ViewModelProviderFactory factory;
    private FavouritesViewModel mainViewModel;
    @Inject
    FavouritesAdapter mGameAdapter;
    @Inject
    LinearLayoutManager mLinearLayout;

    private SwipeController swipeController;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_favourites;
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
        mainViewModel.setNavigator(this);
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
            mainBinding.toolbar.setTitle("Starred Profiles");
        }
        mainBinding.recyclerView.setLayoutManager(mLinearLayout);
        mainBinding.recyclerView.setAdapter(mGameAdapter);
        settingBottomNav();

        settingSwipeController();
    }

    private void settingSwipeController() {
        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mainViewModel.deletePlayerFromDB(position);
            }
        }, 0);

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mainBinding.recyclerView);

        mainBinding.recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    private void settingBottomNav() {
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
    public void updatePlayers(List<PlayerByNickDB> players) {
        mGameAdapter.addItems(players);
    }
}