package com.redli.tmvpsimple.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xianicai on 2017/3/15.
 */

public class RetrofitUtil {

//    private String baseUrl;

    public static final String BASE_URL = "https://api.douban.com/v2/movie/"; //可以用常量类来存一下

    private static final int DEFAULT_TIMEOUT = 5;

    public Retrofit retrofit;

    //构造方法私有
    private RetrofitUtil() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    //获取单例
    public static RetrofitUtil getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
