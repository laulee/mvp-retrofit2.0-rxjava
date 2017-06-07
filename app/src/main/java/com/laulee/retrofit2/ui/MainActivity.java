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

import com.laulee.baseframe.base.BaseActivity;
import com.laulee.retrofit2.R;
import com.laulee.retrofit2.ui.gank.GankFragment;

import butterknife.BindView;

/**
 * Created by laulee on 17/2/26.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_layout_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_layout_navigation)
    NavigationView navigationView;
    @BindView(R.id.main_layout)
    DrawerLayout mainLayout;
    @BindView(R.id.main_layout_frame)
    FrameLayout mainFrameLayout;
    private Fragment currentFragment;

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
        setSupportActionBar( toolbar );
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

    @Override
    protected int setContentViewId() {
        return R.layout.main_actvity;
    }
}
