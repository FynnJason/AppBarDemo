package com.fynnjason.appbardemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fynnjason.appbardemo.databinding.FragmentContentBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author：FynnJason
 * copyright：© 2017 Android.Own.
 */

public class ContentFragment extends Fragment implements OnRefreshListener, OnLoadmoreListener {
    private FragmentContentBinding mBinding;
    private ContentAdapter mAdapter;
    private int num = 1;
    private List<String> mData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBinding.mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.mRecycler.setHasFixedSize(true);
        mAdapter = new ContentAdapter(R.layout.item_content, getData());
        mBinding.mRecycler.setAdapter(mAdapter);
        mBinding.mRefresh.setOnRefreshListener(this);
        mBinding.mRefresh.setOnLoadmoreListener(this);
    }

    private List<String> getData() {
        for (int i = 0; i < 10; i++) {
            mData.add(String.valueOf(num++));
        }
        return mData;
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mData.clear();
                num = 1;
                getData();
                mBinding.mRefresh.finishRefresh();
            }
        }, 1000);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                mAdapter.notifyDataSetChanged();
                mBinding.mRefresh.finishLoadmore();
            }
        }, 1000);
    }
}
