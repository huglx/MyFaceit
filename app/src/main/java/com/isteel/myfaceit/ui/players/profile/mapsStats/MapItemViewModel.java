package com.isteel.myfaceit.ui.players.profile.mapsStats;


import androidx.databinding.ObservableField;

import com.isteel.myfaceit.data.model.ResponseGame;

public class MapItemViewModel {
    public final ObservableField<String> label;
    public final ObservableField<String> img;
    public final ObservableField<String> kd;
    public final ObservableField<String> kr;
    public final ObservableField<String> matches;
    public final ObservableField<String> winrate;


    private final ResponseGame.Segment segment;

    public MapItemViewModel(ResponseGame.Segment segment) {
        this.segment = segment;
        this.kd = new ObservableField<>("kd:" + segment.getStats().getKd());
        this.kr = new ObservableField<>("kr:" + segment.getStats().getKr());
        this.matches = new ObservableField<>("Matches:" +segment.getStats().getMatches());
        this.winrate = new ObservableField<>("Winrate:" +segment.getStats().getWinRate());
        this.label = new ObservableField<>(segment.getLabel());
        this.img =  new ObservableField<>(segment.getImg_regular());
    }

}
