package com.ddmaserati.dd.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import org.litepal.LitePal;


/**
 * dec:  基础app
 * Created by ddmaserati
 * on 2019/4/9.
 */
public class MyApplication extends TinkerApplication {

    private static MyApplication mApplication;

    // 配置tinker热更新
    public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.ddmaserati.dd.app.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        // 初始化LitePal数据库
        LitePal.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MyApplication getInstance() {
        return mApplication;
    }
}
