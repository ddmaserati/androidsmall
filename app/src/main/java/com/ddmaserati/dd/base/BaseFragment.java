package com.ddmaserati.dd.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmaserati.dd.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * dec:  基础fragment
 * Created by ddmaserati
 * on 2019/4/10.
 */
public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;
    protected View mFragmentView;
    protected Activity mActivity;
    protected Bundle mBundle;

    public abstract int getLayout();

    public abstract void initData();

    public abstract void loadData();

    protected abstract void prepareData();

    protected boolean isVisible; // 是否可视
    protected boolean isPrepared;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayout(), container, false);
            unbinder = ButterKnife.bind(this, mFragmentView);
            isPrepared = true;
            mBundle = getArguments();
            if (mBundle != null) {
                prepareData();
            }
            initData();
            loadData();

        }
        ViewGroup parent = (ViewGroup) mFragmentView.getParent();
        if (parent != null) {
            parent.removeView(mFragmentView);
        }
        return mFragmentView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 得到可靠的activity
     */
    public Activity getMyActivity() {
        return mActivity;
    }

    // fragment  可视操作
    private void onVisible() {
        loadData();
    }

    // 不可视操作
    private void onInVisible() {

    }

    /**
     * 显示加载中
     */
    public void showLoading() {
        LoadingDialog.showLoading(mActivity);
    }

    /**
     * 隐藏加载中
     */
    public void hideLoading() {
        LoadingDialog.hideLoading();
    }
}
