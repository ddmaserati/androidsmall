package com.ddmaserati.dd.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ddmaserati.dd.R;
import com.ddmaserati.dd.utlis.AppManager;
import com.ddmaserati.dd.utlis.GlideImageLoader;
import com.ddmaserati.dd.utlis.StatusBarHelp;
import com.ddmaserati.dd.utlis.ToastUtils;
import com.ddmaserati.dd.widget.LoadingDialog;

import butterknife.ButterKnife;

/**
 * dec: 基础activity
 * Created by ddmaserati
 * on 2019/4/9.
 */
public abstract class BaseActivity extends AppCompatActivity {


    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();

    private long firstTime = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSeting();
        setContentView(getLayout());
        setStatusBar();
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GlideImageLoader.lowMemory(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            GlideImageLoader.lowMemory(this);
        }
        GlideImageLoader.trimMemory(this, level);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    private void setSeting() {
        AppManager.getInstance().addActivity(this);
    }


    /**
     * 显示加载中
     */
    public void showLoading() {
        LoadingDialog.showLoading(this);
    }

    /**
     * 隐藏加载中
     */
    public void hideLoading() {
        LoadingDialog.hideLoading();
    }

    private void setStatusBar()
    {

//        StatusBarHelp.setStatusBar(this,true, R.color.colorAccent, R.color.colorAccent);
    }

    public void exit()
    {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtils.shortToast("再按一次退出程序");
            firstTime = secondTime;
        } else {
           AppManager.getInstance().removeAll();
        }
    }
}
