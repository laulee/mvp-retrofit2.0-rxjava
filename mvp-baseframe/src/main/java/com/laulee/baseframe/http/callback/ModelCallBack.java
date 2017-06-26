package com.laulee.baseframe.http.callback;

import com.laulee.baseframe.http.error.ApiException;
import com.laulee.baseframe.http.error.ErrorException;

/**
 * 回调
 * Created by laulee on 17/6/7.
 */

public interface ModelCallBack<T> {

    //成功返回数据
    void onSuccess( T t );

    //错误返回
    void onError( String message );

    //自定义服务器异常或运行时异常或数据解析异常
    void onException( ErrorException exception );

    //自定义接口异常(token过期等预处理)
    void onApiException( ApiException exception );
}
