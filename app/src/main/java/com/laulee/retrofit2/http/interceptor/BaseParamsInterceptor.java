package com.laulee.retrofit2.http.interceptor;

import android.content.Context;

import com.laulee.retrofit2.utils.EncodeUtil;
import com.laulee.retrofit2.utils.SystemUtil;
import com.laulee.retrofit2.utils.TimeUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by laulee on 16/12/25.
 */

public class BaseParamsInterceptor implements Interceptor {

    private Context mContext;
    private String encode;

    public BaseParamsInterceptor( Context context, String encoding ) {
        this.mContext = context;
        this.encode = encoding;
    }

    @Override
    public Response intercept( Chain chain ) throws IOException {
        StringBuffer sb = new StringBuffer( );
        sb.append( encode );
        sb.append( TimeUtil.getYMDTime( ) );

        StringBuffer encodeStr = new StringBuffer( );
        encodeStr.append( EncodeUtil.getMD5( sb.toString( ) ) );
        encodeStr.append( "77d7ysgdfd374677453j5h5h" );

        Request request = chain.request( ).newBuilder( )
                .addHeader( "source", "201" )
                .addHeader( "time", TimeUtil.getYMDTime( ) )
                .addHeader( "lang", "lang" )
                .addHeader( "sign", EncodeUtil.getMD5( encodeStr.toString( ) ) )
                .addHeader( "version", SystemUtil.getAPPVersion( mContext ) )
                .addHeader( "device-udid", SystemUtil.getDeviceOnlyId( mContext ) )
                .build( );
        return chain.proceed( request );
    }
}
