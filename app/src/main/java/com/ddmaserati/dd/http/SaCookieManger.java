package com.ddmaserati.dd.http;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * 描述： h5 cookie
 * 时间： 2019-10-09 15:13
 * 作者：ddmaserati
 */
public class SaCookieManger implements CookieJar {


    private static final String TAG = "SaCookieManger_cookie";
    private static Context mContext;

    public SaCookieManger(Context context){
        mContext = context;
    }


    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        SaasCookieManager.loadCookie(cookies,url.host());

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return new ArrayList<>();
    }
}
