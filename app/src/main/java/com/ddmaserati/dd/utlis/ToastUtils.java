package com.ddmaserati.dd.utlis;

import android.widget.Toast;

import com.ddmaserati.dd.app.MyApplication;

/**
 * dec:  toast 统一管理类
 * Created by ddmaserati
 * on 2019/4/9.
 */
public class ToastUtils {

    private static Toast toast;
    private static Toast getInstance(CharSequence message,int duration)
    {
        if(toast == null)
        {
            toast = Toast.makeText(MyApplication.getInstance(),message,duration);
        }
        else
        {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return toast;
    }

    public static void shortToast(CharSequence message)
    {
        getInstance(message,Toast.LENGTH_SHORT).show();
    }

    public static void longToast(CharSequence message)
    {
        getInstance(message,Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     */
    public static void show(CharSequence message, int duration) {
        getInstance(message, duration).show();
    }
}
