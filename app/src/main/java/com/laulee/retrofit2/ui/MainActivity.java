package com.laulee.retrofit2.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.laulee.retrofit2.R;
import com.laulee.retrofit2.base.BaseActivity;
import com.laulee.retrofit2.presenter.MainPresenter;
import com.laulee.retrofit2.ui.gank.GankFragment;

/**
 * Created by laulee on 17/2/26.
 */

public class MainActivity extends BaseActivity<MainPresenter> {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout mainLayout;
    FrameLayout mainFrameLayout;
    private Fragment currentFragment;

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById( R.id.main_layout_toolbar );
        setSupportActionBar( toolbar );
        mainLayout = (DrawerLayout) findViewById( R.id.main_layout );
        navigationView = (NavigationView) findViewById( R.id.main_layout_navigation );
        mainFrameLayout = (FrameLayout) findViewById( R.id.main_layout_frame );

        ActionBar actionBar = getSupportActionBar( );
        if( actionBar != null ) {
            actionBar.setDisplayHomeAsUpEnabled( true );
            actionBar.setHomeAsUpIndicator( R.mipmap.ic_action_add );
        }

        navigationView.setCheckedItem( R.id.nav_call );
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener( ) {
                    @Override
                    public boolean onNavigationItemSelected( @NonNull MenuItem item ) {
                        mainLayout.closeDrawers( );
                        return true;
                    }
                } );

        switchFragment( new GankFragment( ) );
    }

    private void switchFragment( Fragment fragment ) {
        FragmentTransaction transaction = getSupportFragmentManager( ).beginTransaction( );
        if( fragment != currentFragment ) {
            if( !fragment.isAdded( ) ) {
                transaction.add( R.id.main_layout_frame, fragment ).commitAllowingStateLoss( );
            } else {
                transaction.hide( currentFragment ).show( fragment );
            }
            currentFragment = fragment;
        }
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected int setContentViewId() {
        return R.layout.main_actvity;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter( );
    }
}
