package com.isteel.myfaceit.data;

import com.isteel.myfaceit.data.local.datebase.DbHelper;
import com.isteel.myfaceit.data.local.prefs.PreferencesHelper;
import com.isteel.myfaceit.data.model.ResponsePlayer;
import com.isteel.myfaceit.data.remote.ApiService;

import java.util.List;

public interface DataManager extends ApiService, PreferencesHelper, DbHelper {
}
