package com.laulee.retrofit2.base;

/**
 * Created by laulee on 16/12/25.
 */

public interface IBasePrestener<V extends BaseView> {

    void attach(V view);

    void destoryView();
}
