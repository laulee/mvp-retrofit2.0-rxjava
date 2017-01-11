package com.laulee.retrofit2.http.subscriber;

import com.laulee.retrofit2.http.ApiException;
import com.laulee.retrofit2.utils.SystemUtil;

import rx.Subscriber;

/**
 * Created by laulee on 16/12/26.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext( T t ) {
        _onNext( t );
    }

    @Override
    public void onError( Throwable e ) {
        e.printStackTrace( );
        if( !SystemUtil.isNetworkConnected( ) ) {
            _onError( "网络连接不可用" );
        } else if( e instanceof ApiException ) {
            _onError( e.getMessage( ) );
        } else {
            _onError( "请求失败，请稍后再试..." );
        }
    }

    protected abstract void _onError( String message );

    protected abstract void _onNext( T t );
}
