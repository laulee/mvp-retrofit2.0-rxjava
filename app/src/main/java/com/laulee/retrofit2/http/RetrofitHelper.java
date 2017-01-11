package com.laulee.retrofit2.http;

import android.support.v7.appcompat.BuildConfig;

import com.laulee.retrofit2.app.App;
import com.laulee.retrofit2.app.ApiUrl;
import com.laulee.retrofit2.utils.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by laulee on 16/12/25.
 */

public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;
    private static Retrofit retrofit = null;

    private RetrofitHelper() {
        init( );
    }

    private void init() {
//        initOkHttp( );
        initRetrofit( );
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder( );

        if( BuildConfig.DEBUG ) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor( );
            httpLoggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BASIC );
            builder.addInterceptor( httpLoggingInterceptor );
        }

        File cacheFile = new File(
                App.getInstance( ).getCacheDir( ).getAbsolutePath( ) + File.separator + "data" +
                        "/NetCache" );

        Cache cache = new Cache( cacheFile, 1024 * 1024 * 50 );
        Interceptor cacheInterceptor = new Interceptor( ) {
            @Override
            public Response intercept( Chain chain ) throws IOException {
                Request request = chain.request( );
                if( !SystemUtil.isNetworkConnected( ) ) {
                    request = request.newBuilder( ).cacheControl( CacheControl.FORCE_CACHE )
                            .build( );
                }
                Response response = chain.proceed( request );
                if( SystemUtil.isNetworkConnected( ) ) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder( ).header( "Cache-Control", "public, max-age=" + maxAge )
                            .removeHeader( "Pragma" ).build( );
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder( ).header( "Cache-Control",
                                                   "public, only-if-cached, max-stale=" + maxStale )
                            .removeHeader( "Pragma" ).build( );
                }
                return response;
            }
        };
        //设置缓存
        builder.addNetworkInterceptor( cacheInterceptor );
        builder.addInterceptor( cacheInterceptor );
        builder.cache( cache );
        //设置超时
        builder.connectTimeout( 10, TimeUnit.SECONDS );
        builder.readTimeout( 20, TimeUnit.SECONDS );
        builder.writeTimeout( 20, TimeUnit.SECONDS );
        //错误重连
        builder.retryOnConnectionFailure( true );
        okHttpClient = builder.build( );
    }

    /**
     * 获得retrofit对象
     */
    private void initRetrofit() {
        retrofit = new Retrofit.Builder( ).baseUrl( ApiUrl.BASE_URL ).client( new OkHttpClient() )
                .addConverterFactory( GsonConverterFactory.create( ) )
                .addCallAdapterFactory( RxJavaCallAdapterFactory.create( ) ).build( );
    }

    public Retrofit getRetrofit() {
        return retrofit;
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
