package com.laulee.retrofit2.ui.gank;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by laulee on 17/2/27.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    String[] tabTitle = new String[]{ "Android", "iOS", "Web", "福利" };
    private List<Fragment> fragmentList;

    public FragmentAdapter( FragmentManager fm, List<Fragment> fragmentList ) {
        super( fm );
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem( int position ) {
        if( fragmentList != null )
            return fragmentList.get( position );
        return null;
    }

    @Override
    public int getCount() {
        if( fragmentList != null )
            return fragmentList.size( );
        return 0;
    }

    @Override
    public CharSequence getPageTitle( int position ) {
        return tabTitle[position];
    }
}
