package com.ddmaserati.dd.app;

import android.app.Application;

/**
 * dec:  基础app
 * Created by ddmaserati
 * on 2019/4/9.
 */
public class MyApplication extends Application {

    private static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MyApplication getInstance()
    {
        return mApplication;
    }
}
