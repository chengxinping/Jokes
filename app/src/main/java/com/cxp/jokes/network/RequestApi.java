package com.cxp.jokes.network;

import com.cxp.jokes.model.PicJokesModel;
import com.cxp.jokes.model.TxtJokesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cxp on 17-1-13.
 */

public interface RequestApi {
    @GET("341-1")
    Call<TxtJokesModel> getTxtJokesModel(@Query("maxResult") int maxResult, @Query("page") int page, @Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign, @Query("showapi_timestamp") String showapi_timestamp, @Query("time") String time);

    @GET("341-2")
    Call<PicJokesModel> getPicJokesModel(@Query("maxResult") int maxResult, @Query("page") int page, @Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign, @Query("showapi_timestamp") String showapi_timestamp, @Query("time") String time);
}
