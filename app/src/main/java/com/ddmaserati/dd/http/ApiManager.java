package com.ddmaserati.dd.http;

import android.arch.lifecycle.BuildConfig;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述： rxjava+retrofit+okhttp关联
 * 时间： 2019-09-03 13:47
 * 作者：ddmaserati
 */
public class ApiManager {

    public static final String API_VERSION = "1.0.0";// 当前api版本号
    private static Map<String, String> mapParams;
    private static final boolean isLog = true;


    private static class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            okhttp3.Response response = chain.proceed(chain.request());
            if (isLog) {
                okhttp3.MediaType mediaType = response.body().contentType();
                String content = response.body().string();
                Log.i("request", content);
            }
            return response;
//            return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
        }
    }


    /**
     * 添加头部的参数
     */
    private static class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder().
                            addHeader("app_name", "zintao")
//                    .addHeader("app_version", VersionUtils.getVersionName(AppApplication.getAppContext()))
//                    .addHeader("platform", "android")
//                    .addHeader("datetime", String.valueOf(System.currentTimeMillis()))
//                    .addHeader("device_name", SystemUtils.getDeviceName())
//                    .addHeader("ticks", "com.zintao.apollostore")
//                    .addHeader("did", SystemUtils.getDeviceId())
//                    .addHeader("system_version", SystemUtils.getOSVersion())
//                    .addHeader("utm", "0")
//                    .addHeader("app_network", IntenetUtil.getNetworkType(AppApplication.getAppContext()))
//                    .addHeader("api_version", API_VERSION)
//                    .addHeader("geo_country", "china")
//                    .addHeader("geo_province", "guangdong")
//                    .addHeader("geo_city", "shenzhen")
//                    .addHeader("screen", DisplayUtil.getScreenWidth(AppApplication.getAppContext()) + "*" + DisplayUtil.getScreenHeight(AppApplication.getAppContext()))
//                    .addHeader("carrier", encodeHeadInfo(IntenetUtil.getNetworkOperatorName(AppApplication.getAppContext())))
//                    .addHeader("uid", AppApplication.isLogin() ? AppApplication.getUserId() : "")
//                    .addHeader("channel_id", AppApplication.isLogin() ? AppApplication.getChannleId() : "")
//                    .addHeader("manager_id", AppApplication.isLogin() ? AppApplication.getAccount().getData().getManager_id() : "")
//                    .addHeader("user_name", AppApplication.isLogin()?AppApplication.getAccount().getData().getUsername():"")
                    .build();
            return chain.proceed(request);
        }
    }

//    //初始化okhttp
//    private static OkHttpClient client = new OkHttpClient().newBuilder()//.addNetworkInterceptor(new Ste)
//            .addInterceptor(new LogInterceptor()).addInterceptor(new RequestInterceptor()).cache(new Cache(new File("路径"), 1024 * 1024 * 50)).retryOnConnectionFailure(true)
//            .connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(18, TimeUnit.SECONDS).build();
//
//
//
////       if(BuildConfig.DEBUG) {
////        // Log信息拦截器
////        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
////        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
////        //设置 Debug Log 模式
////        builder.addInterceptor(loggingInterceptor);
////    }
//
//    // 初始化Retrofit
//    private static final Retrofit sRetrofit = new Retrofit.Builder().baseUrl("base路径").client(client)
//            .addConverterFactory(GsonConverterFactory.create())// json
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava作为回调适配器
//            .build();

    /**
     * 设置公共参数
     */
    public static HashMap<String, String> getBasicMap() {
        HashMap<String, String> map = getHeadMap();
//        try {
        String token = "";
//            String token = ParamsTokenFactory.getTokenString(map, false);
        map.put("token", token);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return map;
    }

    public static HashMap<String, String> getHeadMap() {
//        Context context = AppApplication.getAppContext();
        HashMap<String, String> map = new HashMap<>();
//        map.put("app_name", "zintao");
//        map.put("app_version", VersionUtils.getVersionName(context));
//        map.put("platform", "android");
//        map.put("datetime", String.valueOf(System.currentTimeMillis()));
//        map.put("device_name", SystemUtils.getDeviceName());
//        map.put("ticks", "com.zintao.apollostore");
//        map.put("did", SystemUtils.getDeviceId());
//        map.put("system", "android");
//        map.put("system_version", SystemUtils.getOSVersion());
//        map.put("utm", "0");
//        map.put("app_network", IntenetUtil.getNetworkType(context));
//        map.put("api_version", API_VERSION);
//        map.put("geo_country", "中国");
//        map.put("geo_province", "广东省");
//        map.put("geo_city", "深圳市");
//        map.put("screen", DisplayUtil.getScreenWidth(context) + "*" + DisplayUtil.getScreenHeight(context));
//        map.put("carrier", encodeHeadInfo(IntenetUtil.getNetworkOperatorName(context)));
//        if (AppApplication.isLogin()) {
//            map.put("uid", AppApplication.getUserId());
//            map.put("manager_id", AppApplication.getAccount().getData().getManager_id());
//            map.put("channel_id", AppApplication.getChannleId());
//        }
        return map;
    }


}
