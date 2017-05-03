package com.peng_hongru.coder.dagger.module.activity;

import com.peng_hongru.coder.presenter.activity.WebPresenter;
import com.peng_hongru.coder.ui.activity.WebActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 彭鸿儒 on 2017/4/30
 * 邮箱：peng_hongru@163.com
 */
@Module
public class WebModule {
    private WebActivity activity;


    public WebModule(WebActivity activity) {
        this.activity = activity;
    }
    @Provides
    public WebPresenter providerWebPresenter() {
        return new WebPresenter(activity);
    }
}
