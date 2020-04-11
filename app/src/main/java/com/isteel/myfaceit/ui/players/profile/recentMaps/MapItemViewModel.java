package com.isteel.myfaceit.ui.players.profile.recentMaps;


import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponseGame;

public class MapItemViewModel {
    public final ObservableField<String> label;
    public final ObservableField<String> img;

    private final ResponseGame.Segment segment;

    public MapItemViewModel(ResponseGame.Segment segment) {
        this.segment = segment;
        this.label = new ObservableField<>(segment.getLabel());
        this.img =  new ObservableField<>(segment.getImg_regular());
    }

}
