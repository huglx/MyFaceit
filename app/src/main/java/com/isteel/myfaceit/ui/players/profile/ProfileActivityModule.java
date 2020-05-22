package com.isteel.myfaceit.ui.players.profile;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileActivityModule {

    @Provides
    ProfilePagerAdapter provideFeedPagerAdapter(ProfileActivity activity) {
        return new ProfilePagerAdapter(activity.getSupportFragmentManager());
    }
}
