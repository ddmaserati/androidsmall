package com.ddmaserati.dd.utlis;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * dec:  sharepreference管理
 * Created by ddmaserati
 * on 2019/4/10.
 */
public class SharePreUtil {

    private static final String FILE_NAME = "dd_share_data";
    public static final String FIRST_TIME ="first_time";

    public static void put(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String get(Context context, String key) {
        String data = "";
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        data = sp.getString(key, "");
        return data;
    }

    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public static boolean contains(Context context,String key)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }
}
