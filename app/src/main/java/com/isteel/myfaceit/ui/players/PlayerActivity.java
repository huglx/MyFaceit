package com.isteel.myfaceit.ui.players;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.isteel.myfaceit.BR;
import com.isteel.myfaceit.R;
import com.isteel.myfaceit.ViewModelProviderFactory;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.databinding.ActivityPlayerBinding;
import com.isteel.myfaceit.ui.base.BaseActivity;
import com.isteel.myfaceit.ui.games.GameActivity;
import com.isteel.myfaceit.utils.BottomNavigationViewHelper;
import com.isteel.myfaceit.utils.LogUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import javax.inject.Inject;

public class PlayerActivity extends BaseActivity<ActivityPlayerBinding, PlayerViewModel>
        implements PlayerNavigator{

    private ActivityPlayerBinding mainBinding;

    @Inject
    ViewModelProviderFactory factory;

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
        LogUtil.log("dfdf");
        setUp();
        playerViewModel.setNavigator(this);

        Toast.makeText(this, "231321", Toast.LENGTH_SHORT).show();
        mainBinding.recyclerView.setLayoutManager(mLinearLayout);
        mainBinding.recyclerView.setAdapter(mPlayerAdapter);
    }

    private void setUp() {
        setSupportActionBar(mainBinding.toolbar);
        if (getSupportActionBar() != null) {
            mainBinding.toolbar.setTitle("Games");
        }
        BottomNavigationViewHelper.disableShiftMode(mainBinding.bottomNavigation);
        Menu menu = mainBinding.bottomNavigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
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
    public void updatePlayer(List<ResponsePlayer.Player> gameList) {
        mPlayerAdapter.clearItems();
        mPlayerAdapter.addItems(gameList);
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