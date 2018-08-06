package com.org.network.data;

import com.org.network.data.local.AppDatabase;
import com.org.network.data.local.PreferenceHelper;
import com.org.network.data.remote.AppService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppRepository {

    private final AppService mAppService;

    private final AppExecutors mAppExecutors;

    private final AppDatabase mAppDatabase;

    private final PreferenceHelper mPref;

    @Inject
    public AppRepository(AppService appService,
                         AppExecutors appExecutors,
                         AppDatabase appDatabase,
                         PreferenceHelper pref) {
        mAppService = appService;
        mAppExecutors = appExecutors;
        mAppDatabase = appDatabase;
        mPref = pref;
    }

}