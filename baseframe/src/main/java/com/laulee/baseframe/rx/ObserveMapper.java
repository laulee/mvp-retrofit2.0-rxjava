package com.laulee.baseframe.rx;

import com.laulee.baseframe.bean.ResponseBean;
import com.laulee.baseframe.http.error.ApiException;
import com.laulee.baseframe.utils.JsonUtil;
import com.laulee.baseframe.utils.LogUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by laulee on 17/3/13.
 */

public class ObserveMapper {

    public static <T> T mapResult( ResponseBean<T> httpResult ) {
        return httpResult.getValues( );
    }

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>( ) {
            @Override
            public Observable<T> call( Observable<T> observable ) {
                return observable.subscribeOn( Schedulers.io( ) )
                        .observeOn( AndroidSchedulers.mainThread( ) );
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<ResponseBean<T>, T> transformer() {   //compose判断结果
        return new Observable.Transformer<ResponseBean<T>, T>( ) {
            @Override
            public Observable<T> call( Observable<ResponseBean<T>> httpResult ) {
                return httpResult.flatMap( new Func1<ResponseBean<T>, Observable<T>>( ) {
                    @Override
                    public Observable<T> call( ResponseBean<T> httpResult ) {
                        LogUtil.json( JsonUtil.toJson( httpResult ) );
                        if( !httpResult.isError( ) ) {
                            return createData( httpResult.getValues( ) );
                        } else {
                            return Observable
                                    .error( new ApiException( httpResult.getCode( ), "请求错误" ) );
                        }
                    }

                } );
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData( final T data ) {
        return Observable.create( new Observable.OnSubscribe<T>( ) {
            @Override
            public void call( Subscriber<? super T> subscriber ) {
                try {
                    subscriber.onNext( data );
                    subscriber.onCompleted( );
                } catch( Exception e ) {
                    subscriber.onError( e );
                }
            }
        } );
    }
}
