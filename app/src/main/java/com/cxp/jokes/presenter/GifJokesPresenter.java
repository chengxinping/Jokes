package com.cxp.jokes.presenter;

import com.cxp.jokes.model.GifJokesModel;
import com.cxp.jokes.model.PicJokesModel;
import com.cxp.jokes.network.RequestApi;
import com.cxp.jokes.network.RetrofitComentUtil;
import com.cxp.jokes.view.BaseView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cxp on 17-1-22.
 */

public class GifJokesPresenter {
    private BaseView mBaseView;
    private RequestApi mRequestApi;
    private Call<GifJokesModel> call;

    public GifJokesPresenter(BaseView mBaseView) {
        this.mBaseView = mBaseView;
    }

    public void getData(String timestamp, int maxResult) {
        mRequestApi = RetrofitComentUtil.creatService(RequestApi.class);
        call = mRequestApi.getGifJokesModel(maxResult, 1, "30739", "f56b6bc927eb41bf98b7e3a5c9630817", timestamp, "2015-07-10");
        call.enqueue(new Callback<GifJokesModel>() {
            private GifJokesModel mModel;

            @Override
            public void onResponse(Call<GifJokesModel> call, Response<GifJokesModel> response) {
                try {
                    mModel = response.body();
                    mBaseView.success(mModel);
                } catch (Exception e) {
                    e.printStackTrace();
                    mBaseView.fail("请求数据失败");
                }
            }

            @Override
            public void onFailure(Call<GifJokesModel> call, Throwable t) {
                mBaseView.fail("请求数据失败");
            }
        });
    }
}