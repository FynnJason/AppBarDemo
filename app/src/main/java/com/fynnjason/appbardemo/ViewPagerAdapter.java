package com.fynnjason.appbardemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragment;
    private List<String> mTabName;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> Fragments, List<String> TabTile) {
        super(fm);
        this.mFragment = Fragments;
        this.mTabName = TabTile;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    public CharSequence getPageTitle(int position) {
        return mTabName.get(position % mTabName.size());
    }
}
