package com.ddmaserati.dd.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ddmaserati.dd.utlis.AppManager;

/**
 * dec: 基础activity
 * Created by ddmaserati
 * on 2019/4/9.
 */
public abstract class BaseActivity extends AppCompatActivity {


    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSeting();
        setContentView(getLayout());
        initView();
        initData();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    private void setSeting() {
        AppManager.getInstance().addActivity(this);
    }
}
