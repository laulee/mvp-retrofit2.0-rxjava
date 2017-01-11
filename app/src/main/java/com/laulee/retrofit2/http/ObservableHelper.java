package com.laulee.retrofit2.http;

import com.laulee.retrofit2.base.HttpResult;
import com.laulee.retrofit2.http.subscriber.ProgressSubscriber;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by laulee on 16/12/26.
 */

public class ObservableHelper {

    private ObservableHelper() {
    }

    /**
     * 获取单例
     */
    public static ObservableHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 转化成结果数据类型
     *
     * @param <T>
     * @return
     */
    private static <T> Observable.Transformer<HttpResult<T>, T> transformer() {
        return new Observable.Transformer<HttpResult<T>, T>( ) {
            @Override
            public Observable<T> call( Observable<HttpResult<T>> httpResultObservable ) {
                return httpResultObservable.flatMap( new Func1<HttpResult<T>, Observable<T>>( ) {
                    @Override
                    public Observable<T> call( HttpResult<T> tHttpResult ) {
                        if( tHttpResult.getCode( ) == 200 ) {
                            return createData( tHttpResult.getValue( ) );
                        } else
                            return Observable
                                    .error( new ApiException( tHttpResult.getMessage( ) ) );
                    }
                } ).subscribeOn( Schedulers.io( ) ).unsubscribeOn( Schedulers.io( ) )
                        .subscribeOn( Schedulers.io( ) )
                        .observeOn( AndroidSchedulers.mainThread( ) );
            }
        };
    }

    /**
     * 返回数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData( final T data ) {
        return Observable.create( new Observable.OnSubscribe<T>( ) {
            @Override
            public void call( Subscriber<? super T> subscriber ) {
                try {
                    subscriber.onNext( data );
                    subscriber.onCompleted( );
                } catch( RuntimeException e ) {
                    subscriber.onError( e );
                }
            }
        } );
    }

    /**
     * 绑定事件
     *
     * @param ob
     * @param progressSubscriber
     */
    public void toSubscriber( Observable ob, final ProgressSubscriber progressSubscriber ) {
        Observable.Transformer<HttpResult<Object>, Object> objectTransformer = transformer( );
        Observable observable = ob.compose( objectTransformer ).doOnSubscribe( new Action0( ) {
            @Override
            public void call() {

            }
        } );
        observable.subscribe( progressSubscriber );
    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final ObservableHelper INSTANCE = new ObservableHelper( );
    }
}
