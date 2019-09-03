package com.ddmaserati.dd.http;

/**
 * 描述：  参数demo
 * 时间： 2019-09-03 16:16
 * 作者：ddmaserati
 */
public interface ApiParamsDemo {

//    @Query
//    仅带查询参数：http://192.168.0.1/weather?city=北京
//    @GET("weather")
//    Observable<WeatherEntity> getWeather(@Query("city") String city);

//    @Path
//    请求参数直接跟在请求路径下：http://192.168.0.1/weather/北京
//
//    @GET("weather/{city_name}")
//    Observable<Object> getWeather(@Path("city_name") String city_name);

//    @Path和@QueryMap结合
//    此种情形用得比较少：http://192.168.0.1/weather/北京?user_id=1&user_name=jojo
//
//    @GET("weather/{city_name}")
//    Observable<Object> getWeather(@Path("city_name")String city_name, @QueryMap Map<String, String> queryParams);


//    （1）情形一： http://192.168.0.1/comment
//    body参数：{"comment_id":"1","content":"我是评论","user_id":"1001"}
//
//    @Filed 方式处理
//    @FormUrlEncoded //使用@Field时记得添加@FormUrlEncoded
//    @POST("comment")
//    void doComments(@Field("comment_id")String comment_id, @Field("content")String content, @Field("user_id") String user_id);
    //  第二种map@FormUrlEncoded
//@POST("comment")
//void doComments(@FieldMap Map<String, String> paramsMap );
//    通过键值对，以表单的形式提交：
//
//    HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("comment_id","1");
//        hashMap.put("content","我是评论");
//        hashMap.put("user_id","1001");


//    @Body方式处理
//    @POST("comment")
//    void doComments(@Body Object reqBean);
//    @POST("comment")
//    void doComments(@Body List<Object> requestList);


//    (2)情形二：Retrofit文件上传： http://192.168.0.1/upload/
//
//    /**
//     * 文件上传
//     */
//    @POST("upload/")
//    Observable<Object> uploadFile(@Body RequestBody requestBody);
//    只不过文件上传传入的是RequestBody类型,下面是构建RequestBody的方式：
//
//    File file = new File(mFilePath); //mImagePath为上传的文件绝对路径
//    //构建body
//    RequestBody requestBody = new MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file))
//            .build();


    /**********************************/
    // 添加多个头部参数
//    @Headers({
//
//            "Accept: application/vnd.yourapi.v1.full+json",
//
//            "User-Agent: Your-App-Name"
//
//    })
//
//    @GET("/tasks/{task_id}")
//
//    Call getTask(@Path("task_id")longtaskId);
}
