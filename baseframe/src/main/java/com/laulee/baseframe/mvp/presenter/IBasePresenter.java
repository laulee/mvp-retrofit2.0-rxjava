package com.laulee.baseframe.mvp.presenter;

import com.laulee.baseframe.mvp.view.IBaseView;

/**
 * Created by laulee on 16/12/25.
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView( V view );

    void destoryView();
}
