package com.isteel.myfaceit.ui.players;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.ActivityPlayerBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.ui.favourites.FavouritesActivity;
import com.isteel.myfaceit.ui.leaderBoards.LeaderActivity;
import com.isteel.myfaceit.utils.BottomNavigationViewHelper;
import com.isteel.myfaceit.utils.LogUtil;
import com.isteel.myfaceit.utils.SwipeController;
import com.isteel.myfaceit.utils.SwipeControllerActions;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class PlayerActivity extends BaseActivity<ActivityPlayerBinding, PlayerViewModel>
        implements NavigatorPlayer,PlayerAdapter.GameAdapterListener, HasSupportFragmentInjector {

    private ActivityPlayerBinding mainBinding;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    SwipeController swipeController;
    @Inject
    PlayerAdapter mPlayerAdapter;
    @Inject
    LinearLayoutManager mLinearLayout;
    private PlayerViewModel playerViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_player;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, PlayerActivity.class);
    }

    @Override
    public PlayerViewModel getViewModel() {
        playerViewModel = new ViewModelProvider(this,factory).get(PlayerViewModel.class);
        return playerViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = getViewDataBinding();
        setUp();
        playerViewModel.setNavigator(this);
        mPlayerAdapter.setListener(this);
    }

    private void setUp() {
        setSupportActionBar(mainBinding.toolbar);
        if (getSupportActionBar() != null) {
            mainBinding.toolbar.setTitle("Search For Players");
        }
        settingBottomNav();
        settingSwipeController();
    }

    private void settingSwipeController() {
        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                playerViewModel.setPlayerByPos(position);
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mainBinding.recyclerView);
        mainBinding.recyclerView.setLayoutManager(mLinearLayout);
        mainBinding.recyclerView.setAdapter(mPlayerAdapter);

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
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        mainBinding.search.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                playerViewModel.fetchData(mainBinding.search.getText().toString());

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
    public void updatePlayer(List<ResponsePlayer.PlayerByNick> gameList) {
    }

    @Override
    public void test() {

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

    @Override
    public void onRetryClick() {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}