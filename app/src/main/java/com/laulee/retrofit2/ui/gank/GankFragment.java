package com.laulee.retrofit2.ui.gank;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.laulee.retrofit2.R;
import com.laulee.retrofit2.base.BaseFragment;
import com.laulee.retrofit2.base.BasePrestener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laulee on 17/2/27.
 */

public class GankFragment extends BaseFragment {

    ViewPager gankViewPager;
    TabLayout tabLayout;
    FragmentAdapter fragmentPagerAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.gank_fragment_layout;
    }

    @Override
    protected BasePrestener createPresenter() {
        return null;
    }

    @Override
    protected void initParams() {
        gankViewPager.setOffscreenPageLimit( 3 );
        fragmentPagerAdapter = new FragmentAdapter( getFragmentManager( ), initFragment( ) );
        gankViewPager.setAdapter( fragmentPagerAdapter );
        tabLayout.setupWithViewPager( gankViewPager );
    }

    private List<Fragment> initFragment() {
        List<Fragment> fragmentList = new ArrayList<>( );
        fragmentList.add( new AndroidFragment( ) );
        fragmentList.add( new AndroidFragment( ) );
        fragmentList.add( new AndroidFragment( ) );
        fragmentList.add( new AndroidFragment( ) );
        return fragmentList;
    }

    @Override
    protected void initView( View rootView ) {
        tabLayout = (TabLayout) rootView.findViewById( R.id.gank_fragment_layout_tablayout );
        gankViewPager = (ViewPager) rootView.findViewById( R.id.gank_fragment_layout_viewpager );
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad( );
    }
}
