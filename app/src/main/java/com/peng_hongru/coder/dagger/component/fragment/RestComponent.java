package com.peng_hongru.coder.dagger.component.fragment;

import com.peng_hongru.coder.dagger.module.fragment.RestModule;
import com.peng_hongru.coder.ui.fragment.RestFragment;

import dagger.Component;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */
@Component(modules = RestModule.class)
public interface RestComponent {
    void in(RestFragment fragment);
}
