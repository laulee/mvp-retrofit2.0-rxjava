package com.laulee.baseframe.rx;

import com.laulee.baseframe.http.callback.ModelCallBack;
import com.laulee.baseframe.http.error.ApiException;
import com.laulee.baseframe.http.error.ErrorException;
import com.laulee.baseframe.http.error.ExceptionEngine;

import rx.Subscriber;

/**
 * 基类的subscriber处理
 * <p>
 * Created by laulee on 17/6/5.
 */

public class RxSubscriber<T> extends Subscriber<T> {

    private ModelCallBack<T> callBack;

    public RxSubscriber( ModelCallBack<T> callBack ) {
        this.callBack = callBack;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError( Throwable e ) {
        //接口类型错误
        if( e instanceof ApiException ) {
            _onApiError( (ApiException) e );
        } else {
            //网络或解析错误
            _onError( ExceptionEngine.handleException( e ) );
        }
    }

    //接口异常处理，比如token错误
    protected void _onApiError( ApiException e ) {
        callBack.onApiException( e );
    }

    @Override
    public void onNext( T t ) {
        _onSuccess( t );
    }

    //成功返回数据
    protected void _onSuccess( T t ) {
        callBack.onSuccess( t );
    }

    //服务器异常处理以及解析数据错误处理
    protected void _onError( ErrorException e ) {
        callBack.onException( e );
    }

}
