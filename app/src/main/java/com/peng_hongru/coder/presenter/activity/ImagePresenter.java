package com.peng_hongru.coder.presenter.activity;

import com.orhanobut.logger.Logger;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.module.net.bean.ResponseInfo;
import com.peng_hongru.coder.presenter.base.BasePresenter;
import com.peng_hongru.coder.ui.activity.ImageActivity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 彭鸿儒 on 2017/4/30
 * 邮箱：peng_hongru@163.com
 */

public class ImagePresenter extends BasePresenter {

    private int pageNumber = 20;
    private int pageCount = 1;

    private ImageActivity activity;
    private Flowable<ResponseInfo<Information>> girls;

    public ImagePresenter(ImageActivity activity) {
        super();
        this.activity = activity;
    }


    public void loadData() {
        pageCount = 1;
        activity.showLoadingProgressBar();
        girls = api.girls(pageNumber, pageCount);
        girls.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<ResponseInfo<Information>>() {
                            @Override
                            public void accept(@NonNull ResponseInfo<Information> information) throws Exception {
                                if (information != null && !information.isError()) {
                                    activity.refreshImage(information.getResults());
                                } else {
                                    throw new Exception("网络请求错误");
                                }
                                activity.hideLoadingProgressBar();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                activity.hideLoadingProgressBar();
                            }
                        }
                );

    }

    public void loadMore(){
        pageCount = (activity.getDataSize() / pageNumber);
        activity.showLoadingProgressBar();
        girls = api.girls(pageNumber, ++pageCount);
        girls.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<ResponseInfo<Information>>() {
                            @Override
                            public void accept(@NonNull ResponseInfo<Information> information) throws Exception {
                                Logger.d("网络请求成功");
                                if (information != null && !information.isError() && visible) {
                                    List<Information> results = information.getResults();
                                    if (results == null || results.size() == 0) {
                                        // // TODO: 2017/5/2 没有更多了
                                    }
                                    activity.loadMoreImage(results);
                                } else {
                                    throw new Exception("网络请求错误");
                                }
                                activity.hideLoadingProgressBar();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                activity.hideLoadingProgressBar();
                            }
                        }
                );
    }

}
