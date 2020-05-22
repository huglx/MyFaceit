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

package com.isteel.myfaceit.data.local.datebase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.model.db.PlayerByNickDB;
import com.isteel.myfaceit.ui.players.PlayerViewModel;

import java.util.List;

import io.reactivex.Single;
@Dao
public interface ProfileByNickDBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlayerByNickDB p);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PlayerByNickDB> playerByNickList);

    @Query("SELECT * FROM playerByNickList")
    Single<List<PlayerByNickDB>> loadAll();

    @Delete
    void deleteItem(PlayerByNickDB playerByNickDB);
}
