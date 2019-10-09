package com.ddmaserati.dd.http;

import android.os.Build;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;

/**
 * 描述：
 * 时间： 2019-10-09 15:17
 * 作者：ddmaserati
 */
public class SaasCookieManager {

    public static void loadCookie(List<Cookie> cookies,String url){
        List<String> convertCookies  = new ArrayList<>();
        for(int i  = 0,size = cookies.size();i<size;i++)
        {
            String temp = cookies.get(i).toString();
            convertCookies.add(temp);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        for(String aCookies : convertCookies)
        {
            cookieManager.setCookie(url,aCookies);
        }
        if(Build.VERSION.SDK_INT < 21)
        {
            CookieSyncManager.getInstance().sync();
        }
        else{
            cookieManager.flush();
        }
    }
}
