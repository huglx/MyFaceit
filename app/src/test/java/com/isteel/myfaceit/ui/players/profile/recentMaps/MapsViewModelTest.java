package com.isteel.myfaceit.ui.players.profile.recentMaps;

import com.isteel.myfaceit.data.DataManager;
import com.isteel.myfaceit.data.model.ResponseGame;
import com.isteel.myfaceit.rx.TestSchedulerProvider;
import com.isteel.myfaceit.ui.players.profile.mapsStats.MapsViewModel;
import com.isteel.myfaceit.ui.players.profile.mapsStats.NavigatorMaps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MapsViewModelTest {
    @Mock
    NavigatorMaps mCallback;
    @Mock
    DataManager mMockDataManager;
    private MapsViewModel mapsViewModel;
    private TestScheduler mTestScheduler;
    TestSchedulerProvider testSchedulerProvider;

    @Before
    public void setUp() throws Exception {
        mTestScheduler = new TestScheduler();
        testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mapsViewModel = new MapsViewModel(mMockDataManager, testSchedulerProvider);
        mapsViewModel.setNavigator(mCallback);
    }

    @After
    public void tearDown() throws Exception {
        mTestScheduler = null;
        mapsViewModel = null;
        mCallback = null;
    }
    @Test
    public void assertNotNullTest() {
        assertNotNull(mapsViewModel.getSchedulerProvider().io());
        assertNotNull(mCallback);
        assertNotNull(mMockDataManager);
    }
    List<ResponseGame.Segment> segments;

    @Test
    public void fetchData() {
        ResponseGame.Segment segmentMock = new ResponseGame.Segment();

        //segments = Arrays.asList(segmentMock, segmentMock);
        ResponseGame.Csgo csgoMock = new ResponseGame.Csgo();

        csgoMock.setSegmentList(segments);
        doReturn(Single.just(csgoMock))
                .when(mMockDataManager)
                .getStats("123", "csgo");

        mapsViewModel.fetchData("123");

        mTestScheduler.triggerActions();
        verify(mCallback).updatePlayer(segments);
    }

    @Test
    public void errorTest() {
        Throwable throwable = new Exception();

        doReturn(Single.error(throwable))
                .when(mMockDataManager)
                .getStats("123", "csgo");

        mapsViewModel.fetchData("123");

        mTestScheduler.triggerActions();
        verify(mCallback).handleError(throwable);
    }
}
