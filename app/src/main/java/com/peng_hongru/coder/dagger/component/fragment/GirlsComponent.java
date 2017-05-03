package com.peng_hongru.coder.dagger.component.fragment;

import com.peng_hongru.coder.dagger.module.fragment.GirlsModule;
import com.peng_hongru.coder.ui.fragment.GirlsFragment;

import dagger.Component;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */
@Component(modules = GirlsModule.class)
public interface GirlsComponent {
    void in(GirlsFragment fragment);
}
