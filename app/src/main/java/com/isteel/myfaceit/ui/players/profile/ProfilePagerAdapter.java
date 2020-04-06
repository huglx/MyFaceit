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

package com.isteel.myfaceit.ui.players.profile;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.isteel.myfaceit.ui.players.profile.profileInfo.ProfileInfoFragment;
import com.isteel.myfaceit.ui.players.profile.recentMaps.RecentMapsFragment;


/**
 * Created by amitshekhar on 10/07/17.
 */

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;
    private String str;

    public ProfilePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mTabCount = 2;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ProfileInfoFragment.newInstance(str);
            case 1:
                return RecentMapsFragment.newInstance();
            default:
                return null;
        }
    }
}
