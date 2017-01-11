package com.laulee.retrofit2.http.Apis;

import android.support.v7.appcompat.BuildConfig;

import com.laulee.retrofit2.app.ApiUrl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by laulee on 16/12/26.
 */

public class Apis {

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 10000;
    public static Retrofit retrofit;

    private Apis() {
        initRetrofit( );
    }

    /**
     * 获取单例
     */
    public static Apis getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private void initRetrofit() {
        if( retrofit == null ) {
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder( );
            httpClientBuilder.connectTimeout( DEFAULT_TIMEOUT, TimeUnit.SECONDS );

            if( BuildConfig.DEBUG ) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor( );
                httpLoggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BASIC );
                httpClientBuilder.addInterceptor( httpLoggingInterceptor );
            }

            retrofit = new Retrofit.Builder( )
                    .client( httpClientBuilder.build( ) )
                    .addConverterFactory( GsonConverterFactory.create( ) )
                    .addCallAdapterFactory( RxJavaCallAdapterFactory.create( ) )
                    .baseUrl( ApiUrl.BASE_URL )
                    .build( );
        }
    }

    /**
     * 通过静态内部类创建单例
     */
    private static class SingletonHolder {
        private static final Apis INSTANCE = new Apis( );
    }
}
