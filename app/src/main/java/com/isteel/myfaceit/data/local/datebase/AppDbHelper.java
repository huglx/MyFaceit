/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.isteel.myfaceit.data.local.datebase;
import androidx.room.Room;

import com.isteel.myfaceit.data.model.db.PlayerByNickDB;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by amitshekhar on 07/07/17.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<PlayerByNickDB>> getAllPlayers() {
        return mAppDatabase.playerByNickDBDao().loadAll()
                .toObservable();
    }

    @Override
    public Observable<Boolean> insertPlayer(PlayerByNickDB playerByNickDB) {
        return Observable.fromCallable(() -> {
            mAppDatabase.playerByNickDBDao().insert(playerByNickDB);
            return true;
        });
    }

    @Override
    public Observable<Boolean> deletePlayer(PlayerByNickDB playerByNickDB) {
        return Observable.fromCallable(() -> {
            mAppDatabase.playerByNickDBDao().deleteItem(playerByNickDB);
            return true;
        });
    }
}
