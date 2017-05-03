package com.peng_hongru.coder.presenter.base;


import com.peng_hongru.coder.module.net.bean.ResponseInfo;
import com.peng_hongru.coder.presenter.api.RequestApi;
import com.peng_hongru.coder.utils.C;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 彭鸿儒 on 2017/4/18.
 * 邮箱：peng_hongru@163.com
 */

public class BasePresenter {
    protected static RequestApi api;


    public BasePresenter() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(C.Url.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(RequestApi.class);
        }
    }

    public static abstract class CallBackAdapter<T> implements Callback<ResponseInfo> {

        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            if (response != null && response.isSuccessful() && !response.body().isError()) {
                List<T> data = response.body().getResults();
                loadSuccess(data);
            } else {
                loadError(call, new Exception("网络请求失败"));
            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            loadError(call,new Exception("网络请求失败"));
        }


        protected abstract void loadSuccess(List<T> data);

        protected abstract void loadError(Call<ResponseInfo> call, Exception e);


    }

    protected boolean visible = false; // 界面是否可见

    public void onStart() {
        visible = true;
    }

    public void onPause() {
        visible = false;
    }



}
