package com.laulee.baseframe.base;

import android.os.Bundle;

import com.laulee.baseframe.app.AppConfig;
import com.laulee.baseframe.mvp.presenter.RxPresenter;
import com.laulee.baseframe.mvp.view.IBaseView;
import com.laulee.baseframe.utils.PresenterUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by laulee on 16/12/18.
 */

public abstract class RxBaseActivity<P extends RxPresenter> extends RxAppCompatActivity
        implements IBaseView {

    protected P mPresenter; //presenter 对象

    private Unbinder unbinder;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( setContentViewId( ) );
        mPresenter = PresenterUtils.getParadigm( this, 0 );
        if( mPresenter != null )
            mPresenter.attachView( this );
        unbinder = ButterKnife.bind( this );
        //将activity加入到集合中
        AppConfig.addActivity( this );
        initParams( );
    }

    //布局ID
    protected abstract int setContentViewId();

    protected abstract void initParams();

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        if( unbinder != null )
            unbinder.unbind( );
        if( mPresenter != null )
            mPresenter.destoryView( );
        AppConfig.removeActivity( this );
    }

}
