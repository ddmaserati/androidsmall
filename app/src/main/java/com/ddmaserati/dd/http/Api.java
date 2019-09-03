package com.ddmaserati.dd.http;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 描述： 网络请求接口
 * 时间： 2019-09-03 13:43
 * 作者：ddmaserati
 */
public interface Api {


    // 参考https://www.jianshu.com/p/0fda3132cf98
    @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("word/word")
    Call<Object> getNews(@Query("num") String num, @Query("page")String page);
}
