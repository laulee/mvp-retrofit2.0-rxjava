package com.laulee.retrofit2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.laulee.retrofit2.app.AppConfig;

/**
 * Created by laulee on 16/12/18.
 */

public abstract class BaseActivity<P extends BasePrestener> extends AppCompatActivity
        implements BaseView {

    protected P mPresenter; //presenter 对象

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        mPresenter = createPresenter( );
        mPresenter.attach( this ); //presenter与view建立关联
        setContentView( setContentViewId( ) );

        init( );
    }

    /**
     * 初始化数据
     */
    private void init() {
        //将activity加入到集合中
        AppConfig.addActivity( this );
        initView( );
        initParams( );
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化参数
     */
    protected abstract void initParams();

    //设置布局界面ID
    protected abstract int setContentViewId();

    //创建presenter对象
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        mPresenter.destoryView( );
    }
}
