package com.laulee.baseframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by laulee on 17/3/23.
 */

public abstract class BaseFragment extends RxFragment {

    private View rootView;
    private boolean isCreate = false;
    private Unbinder unbinder;
    private String FRAGMENT_STATE_SAVE_IS_HIDDEN = "fragment_state_save_status";

    protected abstract int setLayoutId();

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        resolveOverlapping( savedInstanceState );
    }

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
        unbinder = ButterKnife.bind( this, view );
        initView( view );
        if( savedInstanceState == null ) {
            if( !isHidden( ) ) {
                isCreate = true;
                lazyLoad( );
            }
        } else {
            if( !savedInstanceState.getBoolean( FRAGMENT_STATE_SAVE_IS_HIDDEN ) ) {
                isCreate = true;
                lazyLoad( );
            }
        }
    }

    @Override
    public void onHiddenChanged( boolean hidden ) {
        super.onHiddenChanged( hidden );
        if( !hidden && !isCreate ) {
            isCreate = true;
            lazyLoad( );
        }
    }

    protected void lazyLoad() {
        initParams( );
    }

    protected abstract void initView( View view );

    protected abstract void initParams();

    @Override
    public void onDestroy() {
        super.onDestroy( );
        if( unbinder != null )
            unbinder.unbind( );
    }

    private void resolveOverlapping( Bundle savedInstanceState ) {
        if( savedInstanceState != null ) {
            boolean isHidden = savedInstanceState.getBoolean( FRAGMENT_STATE_SAVE_IS_HIDDEN );
            FragmentTransaction ft = getFragmentManager( ).beginTransaction( );
            if( isHidden ) {
                ft.hide( this );
            } else {
                ft.show( this );
            }
            ft.commit( );
        }
    }

    @Override
    public void onSaveInstanceState( Bundle outState ) {
        super.onSaveInstanceState( outState );
        outState.putBoolean( FRAGMENT_STATE_SAVE_IS_HIDDEN, isHidden( ) );
    }
}
