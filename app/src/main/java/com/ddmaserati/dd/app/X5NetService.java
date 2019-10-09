package com.ddmaserati.dd.app;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tencent.smtt.sdk.QbSdk;

/**
 * 描述：intentservice 启动x5webview
 * 时间： 2019-10-09 11:17
 * 作者：ddmaserati
 */
public class X5NetService extends IntentService {
    public static final String TAG = "LOG_WEBVIEW";

    public X5NetService() {
        super(TAG);
    }

    public X5NetService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        initX5Web();
    }

    private void initX5Web() {
        if (!QbSdk.isTbsCoreInited()) {
            // 设置X5初始化完成的回调接口
            QbSdk.preInit(getApplicationContext(), null);
        }
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
        @Override
        public void onViewInitFinished(boolean arg0) {

        }
        @Override
        public void onCoreInitFinished() {

        }
    };

}
