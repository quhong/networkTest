package com.org.network.di;

import android.app.Application;
import android.content.Context;

import com.org.network.data.AppExecutors;
import com.org.network.data.DiskIOThreadExecutor;
import com.org.network.data.local.AppDatabase;
import com.org.network.data.local.PreferenceHelper;
import com.org.network.data.remote.AppService;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ActivityModule.class})
abstract public class AppRepositoryModule {

    private static final int THREAD_COUNT = 3;

    @Singleton
    @Provides
    static PreferenceHelper providePref(Context context) {
        return new PreferenceHelper(context);
    }

    @Singleton
    @Provides
    static AppDatabase provideDb(Application context) {
        return AppDatabase.getAppDatabase(context);
    }

    @Singleton
    @Provides
    static AppService provideService() {
        return new AppService();
    }

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
            Executors.newFixedThreadPool(THREAD_COUNT),
            new AppExecutors.MainThreadExecutor());
    }

}
