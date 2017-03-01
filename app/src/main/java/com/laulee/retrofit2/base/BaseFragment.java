package com.laulee.retrofit2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by laulee on 17/2/27.
 */

public abstract class BaseFragment<P extends BasePrestener> extends Fragment implements BaseView {

    protected P mPresenter;
    private View rootView;
    private boolean isCreate = false;
    private boolean isVisible = false;

    protected abstract int setLayoutId();

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState ) {
        if( rootView == null ) {
            rootView = inflater.inflate( setLayoutId( ), container, false );
        }

        ViewGroup viewGroup = (ViewGroup) rootView.getParent( );
        if( viewGroup != null ) {
            viewGroup.removeView( rootView );
        }
        return rootView;
    }

    @Override
    public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
        configPresenter( );
        initView( view );
        initParams( );
        isCreate = true;
        lazyLoad( );
    }

    private void configPresenter() {
        mPresenter = createPresenter( );
        if( mPresenter != null )
            mPresenter.attach( this );
    }

    protected abstract P createPresenter();

    protected abstract void initParams();

    protected abstract void initView( View rootView );

    @Override
    public void onDestroy() {
        super.onDestroy( );
        if( mPresenter != null )
            mPresenter.destoryView( );
    }

    @Override
    public void onHiddenChanged( boolean hidden ) {
        super.onHiddenChanged( hidden );
        if( !hidden ) {
            isVisible = true;
            lazyLoad( );
        }
    }

    protected void lazyLoad() {
        if( !isCreate || !isVisible( ) ) {
            return;
        }
    }
}
