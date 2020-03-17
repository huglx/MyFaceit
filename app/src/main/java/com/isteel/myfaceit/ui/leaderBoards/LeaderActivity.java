package com.isteel.myfaceit.ui.leaderBoards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.ActivityLeaderBinding;
import com.isteel.myfaceit.databinding.ActivityPlayerBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.ui.favourites.FavouritesActivity;
import com.isteel.myfaceit.ui.players.PlayerActivity;
import com.isteel.myfaceit.ui.players.PlayerNavigator;
import com.isteel.myfaceit.utils.BottomNavigationViewHelper;
import com.isteel.myfaceit.utils.LogUtil;

import java.util.List;

import javax.inject.Inject;

public class LeaderActivity extends BaseActivity<ActivityLeaderBinding, LeaderBoardsViewModel>
        implements LeaderNavigator{

    private ActivityLeaderBinding mainBinding;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    LeaderAdapter mLeaderAdapter;
    @Inject
    LinearLayoutManager mLinearLayout;

    private LeaderBoardsViewModel leaderBoardsViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leader;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LeaderActivity.class);
    }

    @Override
    public LeaderBoardsViewModel getViewModel() {
        leaderBoardsViewModel = new ViewModelProvider(this,factory).get(LeaderBoardsViewModel.class);
        return leaderBoardsViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = getViewDataBinding();
        LogUtil.log("dfdf");
        setUp();
        leaderBoardsViewModel.setNavigator(this);

        Toast.makeText(this, "111111111", Toast.LENGTH_SHORT).show();
        mainBinding.recyclerView.setLayoutManager(mLinearLayout);
        mainBinding.recyclerView.setAdapter(mLeaderAdapter);
    }

    private void setUp() {
        setSupportActionBar(mainBinding.toolbar);
        if (getSupportActionBar() != null) {
            mainBinding.toolbar.setTitle("Top");
        }
        BottomNavigationViewHelper.disableShiftMode(mainBinding.bottomNavigation);
        Menu menu = mainBinding.bottomNavigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        mainBinding.search.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                leaderBoardsViewModel.fetchData("csgo", "EU");

            }
            return false;
        });

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
                    this.finish();

                    break;
            }
            return true;
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateList(List<ResponsePlayer.Player> playerList) {
        mLeaderAdapter.clearItems();
        mLeaderAdapter.addItems(playerList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mainBinding.searchView.setMenuItem(item);

        return true;*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}