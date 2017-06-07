package com.laulee.baseframe.http.error;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;

/**
 * 错误数据解析
 * Created by laulee on 17/3/2.
 */

public abstract class ErrorHandler<T> implements Action1<Throwable> {

    @Override
    public void call( Throwable throwable ) {
        if( throwable instanceof HttpException ) {
            HttpException error = (HttpException) throwable;
            try {
                onError( new Gson( ).fromJson( error.response( ).errorBody( ).string( ),
                                               getTypeParameterClass( ) ) );
            } catch( IOException e ) {
                onException( e );
                e.printStackTrace( );
            }
        } else {
            onException( throwable );
            throwable.printStackTrace( );
        }
    }

    /**
     * 通过反射获取范型类
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private Class<T> getTypeParameterClass() {
        Type type = getClass( ).getGenericSuperclass( );
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return (Class<T>) parameterizedType.getActualTypeArguments( )[0];
    }

    public abstract void onError( T s );

    public abstract void onException( Throwable e );
}
