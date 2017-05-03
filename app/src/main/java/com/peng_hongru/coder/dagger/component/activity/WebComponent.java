package com.peng_hongru.coder.dagger.component.activity;

import com.peng_hongru.coder.dagger.module.activity.WebModule;
import com.peng_hongru.coder.ui.activity.WebActivity;

import dagger.Component;

/**
 * Created by 彭鸿儒 on 2017/4/30
 * 邮箱：peng_hongru@163.com
 */
@Component(modules = WebModule.class)
public interface WebComponent {
    void in(WebActivity activity);
}
