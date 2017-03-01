package com.laulee.retrofit2.bean.entity;

/**
 * Created by laulee on 17/2/28.
 */

public class GankResult<T> {

    private T results;

    private boolean error;

    public T getResults() {
        return results;
    }

    public boolean isError() {
        return error;
    }
}
