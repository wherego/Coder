package com.peng_hongru.coder.utils;

/**
 * app 配置
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */

public interface C {


    interface Url {
        String BASE_URL = "http://gank.io/api/";
        String DATA = "data/";
        String ANDROID = DATA + "Android";
        String GIRL = DATA + "福利";
        String REST = DATA + "休息视频";

    }


    interface Config {
        String DB_NAME = "coder";
    }
}

