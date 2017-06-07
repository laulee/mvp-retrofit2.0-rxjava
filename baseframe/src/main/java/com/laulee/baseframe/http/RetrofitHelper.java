package com.laulee.baseframe.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by laulee on 17/3/1.
 */

public class RetrofitHelper {

    public RetrofitHelper() {
        //initOkHttpClent( );
    }

    public static  <T> T createService( Class<T> serviceClass, String baseUrl, OkHttpClient okHttpClient ) {
        Retrofit retrofit = new Retrofit.Builder( ).baseUrl( baseUrl )
                .client( okHttpClient )
                .addConverterFactory( GsonConverterFactory.create( ) )
                .addCallAdapterFactory( RxJavaCallAdapterFactory.create( ) ).build( );
        return retrofit.create( serviceClass );
    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    /**
     * 获取单例
     */
    public static RetrofitHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
