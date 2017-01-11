package com.laulee.retrofit2.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.laulee.retrofit2.BuildConfig;
import com.laulee.retrofit2.utils.LogUtil;

public class App extends Application {

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
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

        //初始化屏幕宽高
        getScreenSize( );

        //初始化日志
        LogUtil.initLog( BuildConfig.DEBUG, this );

        //        //初始化错误收集
        //        CrashHandler.init(new CrashHandler(getApplicationContext()));
        //
        //        //初始化内存泄漏检测
        //        LeakCanary.install( this);
        //
        //        //初始化过度绘制检测
        //        BlockCanary.install( this, new AppBlockCanaryContext()).start();
        //
        //        //初始化tbs x5 webview
        //        QbSdk.allowThirdPartyAppDownload(true);
        //        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType
        // .FIRSTUSE_AND_PRELOAD, new QbSdk.PreInitCallback() {
        //            @Override
        //            public void onCoreInitFinished() {
        //            }
        //
        //            @Override
        //            public void onViewInitFinished(boolean b) {
        //            }
        //        });
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this
                .getSystemService( Context.WINDOW_SERVICE );
        DisplayMetrics dm = new DisplayMetrics( );
        Display display = windowManager.getDefaultDisplay( );
        display.getMetrics( dm );
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if( SCREEN_WIDTH > SCREEN_HEIGHT ) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }
}