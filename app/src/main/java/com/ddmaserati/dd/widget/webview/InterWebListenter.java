package com.ddmaserati.dd.widget.webview;

import android.support.annotation.IntRange;

/**
 * 描述： web接口回调
 * 时间： 2019-10-09 16:05
 * 作者：ddmaserati
 */
public interface InterWebListenter {

    void hideProgressBar();

    void showErrorView();

    void startProgress(@IntRange(from = 0, to = 100) int progress);

    void showTitle(String title);
}
