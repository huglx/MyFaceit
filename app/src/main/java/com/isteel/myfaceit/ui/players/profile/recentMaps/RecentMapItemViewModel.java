package com.isteel.myfaceit.ui.players.profile.recentMaps;


import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponseMatch;

public class RecentMapItemViewModel {
    public final ObservableField<String> url;
    public final RecentMapItemViewModelListener mListener;

    private final ResponseMatch.Match match;

    public RecentMapItemViewModel(RecentMapItemViewModelListener mListener, ResponseMatch.Match match) {
        this.mListener = mListener;
        this.match = match;
        url = new ObservableField<>(match.getUrl().replace("{lang}","ru"));

    }

    public void onItemClick() {
        mListener.onItemClick(match.getUrl().replace("{lang}","ru"));
    }

    public interface RecentMapItemViewModelListener {
        void onItemClick(String url);
    }

}
