package com.org.network.data.remote;


import com.org.network.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class AppService {

    private static volatile AppService mAppService = null;

    private final OkHttpClient mOkHttpClient;

    public AppService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ?
            HttpLoggingInterceptor.Level.HEADERS :
            HttpLoggingInterceptor.Level.NONE);
        mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build();
    }

    public static AppService get() {
        AppService inst = mAppService;
        if (inst == null) {
            synchronized (AppService.class) {
                inst = mAppService;
                if (inst == null) {
                    inst = new AppService();
                    mAppService = inst;
                }
            }
        }
        return inst;
    }

    private final MediaType XML = MediaType.parse("application/xml; charset=utf-8");


}
