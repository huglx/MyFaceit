package com.isteel.myfaceit.data.local.datebase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.isteel.myfaceit.data.local.datebase.dao.ProfileByNickDBDao;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.model.db.PlayerByNickDB;


@Database(entities = {PlayerByNickDB.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProfileByNickDBDao playerByNickDBDao();
}
