package com.isteel.myfaceit.ui.players;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.rx.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PlayerViewModelTest {
    @Mock
    NavigatorPlayer mPlayerCallback;
    @Mock
    DataManager mMockDataManager;
    private PlayerViewModel playerViewModel;
    private TestScheduler mTestScheduler;
    TestSchedulerProvider testSchedulerProvider;

    @Before
    public void setUp() throws Exception {
        mTestScheduler = new TestScheduler();
        testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        playerViewModel = new PlayerViewModel(mMockDataManager, testSchedulerProvider);
        playerViewModel.setNavigator(mPlayerCallback);
    }

    @After
    public void tearDown() throws Exception {
        mTestScheduler = null;
        playerViewModel = null;
        mPlayerCallback = null;

    }
    @Test
    public void assertNotNullTest() {
        assertNotNull(playerViewModel.getSchedulerProvider().io());
        assertNotNull(mPlayerCallback);
        assertNotNull(mMockDataManager);
        mMockDataManager.setGame("csgo");
    }
    @Test
    public void testFetchData() {
        ResponsePlayer responsePlayer = new ResponsePlayer();
        List<ResponsePlayer.PlayerByNick> playerByNicks =new ArrayList<>();
        responsePlayer.setItems(playerByNicks);

        doReturn(Single.just(responsePlayer))
                .when(mMockDataManager)
                .getPlayerByNick("123", "csgo");

        playerViewModel.fetchData("123");

        mTestScheduler.triggerActions();
        verify(mPlayerCallback).updatePlayer(responsePlayer.getItems());
    }

    @Test
    public void testError() {
        Throwable throwable = new Exception();

        doReturn(Single.error(throwable))
                .when(mMockDataManager)
                .getPlayerByNick("123", "csgo");

        playerViewModel.fetchData("123");

        mTestScheduler.triggerActions();
        verify(mPlayerCallback).handleError(throwable);
    }
}
