package com.laulee.baseframe.base;

import android.os.Bundle;

import com.laulee.baseframe.app.AppConfig;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by laulee on 17/3/23.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( setContentViewId( ) );
        unbinder = ButterKnife.bind( this );
        //将activity加入到集合中
        AppConfig.addActivity( this );
        initParams( );
    }

    /**
     * 初始化参数
     */
    protected abstract void initParams();

    //设置布局界面ID
    protected abstract int setContentViewId();

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        if( unbinder != null )
            unbinder.unbind( );
        AppConfig.removeActivity( this );
    }
}
