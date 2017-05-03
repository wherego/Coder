package com.peng_hongru.coder.dagger.module.fragment;

import com.peng_hongru.coder.presenter.fragment.InformationPresenter;
import com.peng_hongru.coder.ui.fragment.InformationFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */
@Module
public class InformationModule {

    private InformationFragment fragment;


    public InformationModule(InformationFragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    public InformationPresenter providerInformationPresenter() {
        return new InformationPresenter(fragment);
    }
}
