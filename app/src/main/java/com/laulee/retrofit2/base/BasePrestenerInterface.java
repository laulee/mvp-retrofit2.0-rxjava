package com.laulee.retrofit2.base;

/**
 * Created by laulee on 16/12/25.
 */

public interface BasePrestenerInterface<V extends BaseView> {

    void attach(V view);

    void destoryView();
}
