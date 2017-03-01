package com.laulee.retrofit2.http;

import com.laulee.retrofit2.bean.entity.GankResult;
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
    private static <T> Observable.Transformer<GankResult<T>, T> transformer() {
        return new Observable.Transformer<GankResult<T>, T>( ) {
            @Override
            public Observable<T> call( Observable<GankResult<T>> httpResultObservable ) {
                return httpResultObservable.flatMap( new Func1<GankResult<T>, Observable<T>>( ) {
                    @Override
                    public Observable<T> call( GankResult<T> tHttpResult ) {
                        if( !tHttpResult.isError( ) ) {
                            return createData( tHttpResult.getResults( ) );
                        } else
                            return Observable.error( new ApiException( "错误" ) );
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
        Observable.Transformer<GankResult<Object>, Object> objectTransformer = transformer( );
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
