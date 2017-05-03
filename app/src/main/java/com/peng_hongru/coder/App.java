package com.peng_hongru.coder;

import android.app.Application;
import android.content.Context;

import com.peng_hongru.coder.module.dao.DbHelper;

/**
 * Created by 彭鸿儒 on 2017/4/18.
 * 邮箱：peng_hongru@163.com
 */

public class App extends Application {

    private static Context context;

    public static Context getAppContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        DbHelper.getWriteSession();
    }




}
