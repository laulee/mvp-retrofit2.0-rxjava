package com.laulee.retrofit2.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.laulee.baseframe.utils.LogUtil;
import com.laulee.retrofit2.BuildConfig;

public class App extends Application {

    private static App instance;

    static {
        AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO );
    }

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate( );
        instance = this;

        //初始化日志
        LogUtil.initLog( BuildConfig.DEBUG, this );
    }
}