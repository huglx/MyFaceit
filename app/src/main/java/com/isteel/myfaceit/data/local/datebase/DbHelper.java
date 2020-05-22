package com.isteel.myfaceit.data.local.datebase;

import com.isteel.myfaceit.data.model.db.PlayerByNickDB;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {
    Observable<List<PlayerByNickDB>> getAllPlayers();

    Observable<Boolean> insertPlayer(PlayerByNickDB playerByNickDB);

    Observable<Boolean> deletePlayer(PlayerByNickDB playerByNickDB);

}
