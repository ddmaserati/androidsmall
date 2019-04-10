package com.ddmaserati.dd.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MyApplication getInstance()
    {
        return mApplication;
    }
}
