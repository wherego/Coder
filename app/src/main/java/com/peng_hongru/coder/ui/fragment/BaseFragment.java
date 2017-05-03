package com.peng_hongru.coder.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.peng_hongru.coder.ui.activity.BaseActivity;

/**
 * Created by 彭鸿儒 on 2017/4/18.
 * 邮箱：peng_hongru@163.com
 */

public class BaseFragment extends Fragment {
    //获得状态栏高度
    protected int getStatusBarHeight(){
        BaseActivity baseActivity;
        try {
            baseActivity = (BaseActivity) getActivity();
        } catch (Exception e) {
            return 0;
        }
        return baseActivity.getStatusBarHeight();
    }
    //弹土司
    protected void toast(String msg) {
        BaseActivity baseActivity;
        try {
            baseActivity = (BaseActivity) getActivity();
            baseActivity.toast(msg);
        } catch (Exception e) {

        }
    }
    //使用自定义的界面切换动画
    public void startActivityWithAnimator(Intent intent) {
        BaseActivity baseActivity;
        try {
            baseActivity = (BaseActivity) getActivity();
            baseActivity.starActivityWithAnimator(intent);
        } catch (Exception e) {

        }
    }
}
