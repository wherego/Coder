package com.peng_hongru.coder.dagger.module.fragment;

import com.peng_hongru.coder.presenter.fragment.GirlsPresenter;
import com.peng_hongru.coder.ui.fragment.GirlsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */
@Module
public class GirlsModule {

    private GirlsFragment fragment;


    public GirlsModule(GirlsFragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    public GirlsPresenter providerInformationPresenter() {
        return new GirlsPresenter(fragment);
    }
}
