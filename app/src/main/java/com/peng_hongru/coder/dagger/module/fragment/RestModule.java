package com.peng_hongru.coder.dagger.module.fragment;

import com.peng_hongru.coder.presenter.fragment.RestPresenter;
import com.peng_hongru.coder.ui.fragment.RestFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */
@Module
public class RestModule {

    private RestFragment fragment;


    public RestModule(RestFragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    public RestPresenter providerInformationPresenter() {
        return new RestPresenter(fragment);
    }
}
