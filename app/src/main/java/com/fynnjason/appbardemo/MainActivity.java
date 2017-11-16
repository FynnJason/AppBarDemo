package com.fynnjason.appbardemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.fynnjason.appbardemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initTab();
    }

    private void initTab() {
        mBinding.mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < getTabTitle().size(); i++) {
            mFragments.add(new ContentFragment());
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, getTabTitle());
        mBinding.mViewPager.setAdapter(adapter);
        mBinding.mTableLayout.setupWithViewPager(mBinding.mViewPager);
    }

    private List<String> getTabTitle() {
        return Arrays.asList("新闻", "热点", "游戏", "视频", "娱乐","动画","社会");
    }
}
