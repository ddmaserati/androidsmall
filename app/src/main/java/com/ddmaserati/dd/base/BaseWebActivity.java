package com.ddmaserati.dd.base;

import android.view.ViewGroup;

import com.ddmaserati.dd.widget.webview.X5Webview;

/**
 * 描述： webview 基础类
 * 时间： 2019-10-09 15:36
 * 作者：ddmaserati
 */
public abstract class BaseWebActivity extends BaseActivity {

    protected X5Webview x5Webview;

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(x5Webview != null)
        {
            x5Webview.getSettings().setJavaScriptEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(x5Webview != null)
        {
            x5Webview.getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        if (x5Webview != null && x5Webview.getParent() != null) {
            ((ViewGroup) x5Webview.getParent()).removeView(x5Webview);
            x5Webview.destroy();
            x5Webview = null;
            ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
            viewGroup.removeAllViews();
        }
        super.onDestroy();
    }
}
