package com.peng_hongru.coder.dagger.module.activity;

import com.peng_hongru.coder.presenter.activity.ImagePresenter;
import com.peng_hongru.coder.ui.activity.ImageActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 彭鸿儒 on 2017/4/30
 * 邮箱：peng_hongru@163.com
 */
@Module
public class ImageModule {
    private ImageActivity activity;


    public ImageModule(ImageActivity activity) {
        this.activity = activity;
    }
    @Provides
    public ImagePresenter providerImagePresenter() {
        return new ImagePresenter(activity);
    }
}
