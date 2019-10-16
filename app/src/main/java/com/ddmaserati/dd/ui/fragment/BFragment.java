package com.ddmaserati.dd.ui.fragment;

import android.support.annotation.NonNull;

import com.ddmaserati.dd.R;
import com.ddmaserati.dd.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * dec:
 * Created by ddmaserati
 * on 2019/4/11.
 */
public class BFragment extends BaseFragment {


    private SmartRefreshLayout smartRefreshLayout;

    @Override
    public int getLayout() {
        return R.layout.fragment_b_layout;
    }

    @Override
    public void initData() {
        smartRefreshLayout = mFragmentView.findViewById(R.id.refreshlayout);
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
              refreshLayout.finishRefresh();
            }
        });


    }

    @Override
    public void loadData() {

    }

    @Override
    protected void prepareData() {

    }
}
