package com.laulee.retrofit2.bean.entity;

import com.laulee.baseframe.bean.ResponseBean;

/**
 * Created by laulee on 17/2/28.
 */

public class GankResult<T> extends ResponseBean<T> {

    private T results;

    private boolean error;

    @Override
    public int getCode() {
        return 0;
    }

    public boolean isError() {
        return error;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public T getValues() {
        return results;
    }
}
