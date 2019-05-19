package com.ddmaserati.dd.utlis;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * dec:  屏幕工具
 * Created by ddmaserati
 * on 2019/4/11.
 */
public class ScreenUtils {

    /**
     * Get the width of the screen.
     *获得屏幕宽度
     * @param context
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
        }
        return metrics.widthPixels;
    }

    /**
     * Get the height of the screen.
     *获得屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
        }
        return metrics.heightPixels;
    }

    /**
     *
     * Whether the Status bar is hidden or not,the method always helps you get
     * the height of Status bar.
     *获得状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int id = (Integer) (clazz.getField("status_bar_height").get(object));
            statusHeight = context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

}
