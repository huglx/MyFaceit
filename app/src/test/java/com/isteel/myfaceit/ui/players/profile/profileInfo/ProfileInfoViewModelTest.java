package com.isteel.myfaceit.ui.players.profile.profileInfo;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.rx.TestSchedulerProvider;
import com.isteel.myfaceit.ui.players.NavigatorPlayer;
import com.isteel.myfaceit.ui.players.PlayerViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfileInfoViewModelTest {
    @Mock
    NavigatorPlayerProfileInfo mPlayerCallback;
    @Mock
    DataManager mMockDataManager;
    private ProfileInfoViewModel profileInfoViewModel;
    private TestScheduler mTestScheduler;
    TestSchedulerProvider testSchedulerProvider;

    @Before
    public void setUp() throws Exception {
        mTestScheduler = new TestScheduler();
        testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        profileInfoViewModel = new ProfileInfoViewModel(mMockDataManager, testSchedulerProvider);
        profileInfoViewModel.setNavigator(mPlayerCallback);
    }

    @After
    public void tearDown() throws Exception {
        mTestScheduler = null;
        profileInfoViewModel = null;
        mPlayerCallback = null;

    }
    @Test
    public void assertNotNullTest() {
        assertNotNull(profileInfoViewModel.getSchedulerProvider().io());
        assertNotNull(mPlayerCallback);
        assertNotNull(mMockDataManager);
    }

    @Test
    public void fetchData() {
        ResponsePlayer.Player player = new ResponsePlayer.Player();

        doReturn(Single.just(player))
                .when(mMockDataManager)
                .getPlayerProfile("123");

        profileInfoViewModel.fetchData("123");

        mTestScheduler.triggerActions();
        verify(mPlayerCallback).updatePlayer(player);
    }

    @Test
    public void errorTest() {
        Throwable throwable = new Exception();

        doReturn(Single.error(throwable))
                .when(mMockDataManager)
                .getPlayerProfile("123");

        profileInfoViewModel.fetchData("123");

        mTestScheduler.triggerActions();
        verify(mPlayerCallback).handleError(throwable);
    }
}
