package com.ddmaserati.dd.utlis;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ddmaserati.dd.R;

/**
 * 描述：  状态栏管理
 * 时间： 2019-09-09 14:07
 * 作者：ddmaserati
 */
public class StatusBarHelp {

    private static int statusBarHeight = 0;


    public static void resetStatusBarHeight(Activity activity) {
        if (statusBarHeight <= 0) {
            Rect rectangle = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
            statusBarHeight = rectangle.top;
            if (statusBarHeight <= 0) {
                Resources resources = activity.getResources();
                int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
                statusBarHeight = resources.getDimensionPixelSize(resourceId);
            }
        }
    }


    public static int getStatusBarHeight(Activity activity) {
        if (statusBarHeight <= 0) {
            resetStatusBarHeight(activity);
        }
        return statusBarHeight;
    }



    public static void setStatusBar(@NonNull Activity activity, boolean needLightStatusBar, @ColorRes int statusBarMColor, @ColorRes int statusBarColor) {
        final Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(activity.getResources().getColor(statusBarMColor));
                if (needLightStatusBar) {
                    enableLightStatusBar(activity);
                }
            } else {

                    window.setStatusBarColor(activity.getResources().getColor(statusBarColor));

            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setTintResource(statusBarColor);
        }

    }

    public static void enableLightStatusBar(Activity activity) {
        //如果大于6.0,开启反色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final View decorView = activity.getWindow().getDecorView();
            int visibility = decorView.getSystemUiVisibility();
            visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(visibility);
        }
    }

    public static void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = activity.getWindow().getDecorView();
            int flags = view.getSystemUiVisibility();
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static void showStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(R.color.transparent));
            } else {
                SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
                mTintManager.setStatusBarTintEnabled(false);
            }
        }
    }

    public static void addMarginStatusBarHeight(Activity activity) {
        final int statusBarHeight = StatusBarHelp.getStatusBarHeight(activity);
        View mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        if (mContentView instanceof ViewGroup) {
            View mChildView = ((ViewGroup) mContentView).getChildAt(0);
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                lp.topMargin = statusBarHeight;
                mChildView.setLayoutParams(lp);
            }
        }
    }

    public static void clearMarginStatusBarHeight(Activity activity) {
        final int statusBarHeight = StatusBarHelp.getStatusBarHeight(activity);
        View mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        if (mContentView instanceof ViewGroup) {
            View mChildView = ((ViewGroup) mContentView).getChildAt(0);
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                if (lp != null && lp.topMargin >= statusBarHeight) {
                    lp.topMargin -= statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
            }
        }
    }

    public static void showStatusBarColor(Activity activity, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
            } else {
                SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintResource(color);
            }
        }
    }
}
