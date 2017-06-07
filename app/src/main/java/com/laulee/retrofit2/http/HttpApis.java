package com.laulee.retrofit2.http;

import com.laulee.baseframe.http.RetrofitHelper;
import com.laulee.retrofit2.http.constant.APIUrl;
import com.laulee.retrofit2.http.Apis.GankApis;

import okhttp3.OkHttpClient;

/**
 * Created by laulee on 16/12/26.
 */

public class HttpApis {

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 10000;
    private static GankApis gankApis;

    private HttpApis() {
        init( );
    }

    /**
     * 获取单例
     */
    public static HttpApis getInstance() {
        return HttpApis.SingletonHolder.INSTANCE;
    }

    public static GankApis getGankApis() {
        return getInstance( ).gankApis;
    }

    private void init() {
        gankApis = RetrofitHelper.createService( GankApis.class, APIUrl.HOST, new OkHttpClient( ) );
    }

    /**
     * 通过静态内部类创建单例
     */
    private static class SingletonHolder {
        private static final HttpApis INSTANCE = new HttpApis( );
    }
}
