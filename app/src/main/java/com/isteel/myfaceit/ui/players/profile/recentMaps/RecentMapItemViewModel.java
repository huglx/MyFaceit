package com.isteel.myfaceit.ui.players.profile.recentMaps;


import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponseMatch;

public class RecentMapItemViewModel {
    public final ObservableField<String> url;
    public final ObservableField<String> team1;
    public final ObservableField<String> team2;

    public final RecentMapItemViewModelListener mListener;

    private final ResponseMatch.Match match;

    public RecentMapItemViewModel(RecentMapItemViewModelListener mListener, ResponseMatch.Match match) {
        this.mListener = mListener;
        this.match = match;
        team1 = new ObservableField<>(match.getTeam().getFraction1().getNickName());
        team2 = new ObservableField<>(match.getTeam().getFraction2().getNickName());
        url = new ObservableField<>(match.getUrl().replace("{lang}","ru"));

    }

    public void onItemClick() {
        mListener.onItemClick(match.getId());
    }

    public interface RecentMapItemViewModelListener {
        void onItemClick(String url);
    }

}
