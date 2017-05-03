package com.peng_hongru.coder.dagger.component.fragment;

import com.peng_hongru.coder.dagger.module.fragment.InformationModule;
import com.peng_hongru.coder.ui.fragment.InformationFragment;

import dagger.Component;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */
@Component(modules = InformationModule.class)
public interface InformationComponent {
    void in(InformationFragment fragment);
}
