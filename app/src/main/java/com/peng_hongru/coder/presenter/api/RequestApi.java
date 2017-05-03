package com.peng_hongru.coder.presenter.api;


import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.module.net.bean.ResponseInfo;
import com.peng_hongru.coder.utils.C;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */

public interface RequestApi {


    /*数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    请求个数： 数字，大于0
    第几页：数字，大于0*/

    @GET(C.Url.ANDROID + "/{number}/{page}")
    Flowable<ResponseInfo<Information>> information(@Path("number") int number, @Path("page") int page);

    @GET(C.Url.GIRL + "/{number}/{page}")
    Flowable<ResponseInfo<Information>> girls(@Path("number") int number, @Path("page") int page);

    @GET(C.Url.REST + "/{number}/{page}")
    Flowable<ResponseInfo<Information>> rest(@Path("number") int number, @Path("page") int page);

}
