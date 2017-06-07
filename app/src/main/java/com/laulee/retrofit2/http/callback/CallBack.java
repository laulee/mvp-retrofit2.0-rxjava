package com.laulee.retrofit2.http.callback;

import com.laulee.baseframe.http.callback.ModelCallBack;
import com.laulee.baseframe.http.error.ApiException;

/**
 * Created by laulee on 17/6/7.
 */

public abstract class CallBack<T> implements ModelCallBack<T> {

    @Override
    public void onApiException( ApiException exception ) {

    }

    @Override
    public void onException( Exception exception ) {

    }
}
