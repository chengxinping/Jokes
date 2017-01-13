package com.cxp.jokes.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cxp on 17-1-13.
 */

public class RetrofitComentUtil {
    public static final String BASE_URL = "https://route.showapi.com/";
    public static int DEFAULT_TIMEOUT = 5;

    public static OkHttpClient mClient = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mClient)
            .build();

    private static void TransformationUrl(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build();
    }

    public static <T> T creatService(Class<T> mClass) {
        return retrofit.create(mClass);
    }
}
