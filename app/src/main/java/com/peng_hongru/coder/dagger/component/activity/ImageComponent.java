package com.peng_hongru.coder.dagger.component.activity;

import com.peng_hongru.coder.dagger.module.activity.ImageModule;
import com.peng_hongru.coder.ui.activity.ImageActivity;

import dagger.Component;

/**
 * Created by 彭鸿儒 on 2017/4/30
 * 邮箱：peng_hongru@163.com
 */
@Component(modules = ImageModule.class)
public interface ImageComponent {
    void in(ImageActivity activity);
}
