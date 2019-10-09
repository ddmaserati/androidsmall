package com.ddmaserati.dd.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.ddmaserati.dd.app.MyApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述：
 * 时间： 2019-10-09 15:22
 * 作者：ddmaserati
 */
public class AddCookiesInterceptor implements Interceptor{
    private static final String COOKIE_PREF = "cookies_prefs";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String cookie = getCookie(request.url().toString(), request.url().host());
        if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("Cookie", cookie);
            Log.i(COOKIE_PREF, "interceptor addHeader Cookie: "+cookie);
        }
        return chain.proceed(builder.build());
    }


    private String getCookie(String url, String domain) {

        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(COOKIE_PREF,
                Context.MODE_PRIVATE);

        String cookie = sp.getString(domain, "");



        if (!TextUtils.isEmpty(domain) && sp.contains(domain) && !
                TextUtils.isEmpty(sp.getString(domain, ""))) {
            return sp.getString(domain, "");
        }
        return null;
    }


}
