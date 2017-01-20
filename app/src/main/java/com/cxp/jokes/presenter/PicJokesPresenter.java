package com.cxp.jokes.presenter;

import com.cxp.jokes.model.PicJokesModel;
import com.cxp.jokes.network.RequestApi;
import com.cxp.jokes.network.RetrofitComentUtil;
import com.cxp.jokes.view.BaseView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cxp on 17-1-13.
 */

public class PicJokesPresenter {
    private BaseView mBaseView;
    private RequestApi mRequestApi;
    private Call<PicJokesModel> call;

    public PicJokesPresenter(BaseView mBaseView) {
        this.mBaseView = mBaseView;
    }

    public void getData(String timestamp, int maxResult) {
        mRequestApi = RetrofitComentUtil.creatService(RequestApi.class);
        call = mRequestApi.getPicJokesModel(maxResult, 1, "30739", "f56b6bc927eb41bf98b7e3a5c9630817", timestamp, "2015-07-10");
        call.enqueue(new Callback<PicJokesModel>() {
            private PicJokesModel mModel;

            @Override
            public void onResponse(Call<PicJokesModel> call, Response<PicJokesModel> response) {
                try {
                    mModel = response.body();
                    mBaseView.success(mModel);
                } catch (Exception e) {
                    e.printStackTrace();
                    mBaseView.fail("请求数据失败");
                }
            }

            @Override
            public void onFailure(Call<PicJokesModel> call, Throwable t) {
                mBaseView.fail("请求数据失败");
            }
        });
    }
}
