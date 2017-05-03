package com.peng_hongru.coder.presenter.fragment;

import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.module.net.bean.ResponseInfo;
import com.peng_hongru.coder.presenter.base.BasePresenter;
import com.peng_hongru.coder.ui.fragment.RestFragment;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */

public class RestPresenter extends BasePresenter {

    private RestFragment fragment;
    private int pageNumber = 20;
    private int pageCount = 1;
    private Flowable<ResponseInfo<Information>> rest;

    public RestPresenter(RestFragment fragment) {
        super();
        this.fragment = fragment;
    }

    //刷新操作
    public void loadData() throws Exception {
        pageCount = 1;
        rest = api.rest(pageNumber, pageCount);
        rest.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<ResponseInfo<Information>>() {
                    @Override
                    public boolean test(@NonNull ResponseInfo<Information> informationResponseInfo) throws Exception {
                        return visible;
                    }
                })
                .subscribe(
                        new Consumer<ResponseInfo<Information>>() {
                            @Override
                            public void accept(@NonNull ResponseInfo<Information> information) throws Exception {
                                if (information != null && !information.isError()) {
                                    fragment.refresh(information.getResults());
                                } else {
                                    throw new Exception("网络请求错误");
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                fragment.refreshComplete();
                            }
                        }
                );
    }

    //加载更多操作
    public void loadMore() throws Exception {
        rest = api.rest(pageNumber, ++pageCount);
        rest.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<ResponseInfo<Information>>() {
                    @Override
                    public boolean test(@NonNull ResponseInfo<Information> informationResponseInfo) throws Exception {
                        return visible;
                    }
                })
                .subscribe(
                        new Consumer<ResponseInfo<Information>>() {
                            @Override
                            public void accept(@NonNull ResponseInfo<Information> information) throws Exception {
                                if (information != null && !information.isError()) {
                                    List<Information> results = information.getResults();
                                    if (results == null || results.size() == 0) {
                                        fragment.loadNoMore();
                                    }
                                    fragment.loadMore(results);
                                } else {
                                    throw new Exception("网络请求错误");
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                fragment.loadMoreComplete();
                            }
                        }
                );
    }

}
