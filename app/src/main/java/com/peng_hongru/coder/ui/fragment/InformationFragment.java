package com.peng_hongru.coder.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.peng_hongru.coder.R;
import com.peng_hongru.coder.dagger.component.fragment.DaggerInformationComponent;
import com.peng_hongru.coder.dagger.module.fragment.InformationModule;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.presenter.fragment.InformationPresenter;
import com.peng_hongru.coder.ui.activity.WebActivity;
import com.peng_hongru.coder.ui.adapter.InformationAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */

public class InformationFragment extends BaseFragment {

    @Inject
    public InformationPresenter presenter;

    @InjectView(R.id.xrv_information_container)
    XRecyclerView mRecyclerView;
    private InformationAdapter mAdapter;
    private List<Information> listData;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_information, null);
        ButterKnife.inject(this, view);
        //dagger2 初始化操作
        DaggerInformationComponent component = (DaggerInformationComponent) DaggerInformationComponent.builder()
                .informationModule(new InformationModule(this))
                .build();
        component.in(this);
        //recyclerview 初始化
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() { // 下拉刷新
                try {
                    presenter.loadData();
                } catch (Exception e) {
                    mRecyclerView.refreshComplete();
                    toast(getString(R.string.net_request_error));
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadMore() {//上拉加载更多
                try {
                    presenter.loadMore();
                } catch (Exception e) {
                    mRecyclerView.setNoMore(true);
                    e.printStackTrace();
                }
            }
        });
        listData = new ArrayList<>();
        mAdapter = new InformationAdapter(listData, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
        mAdapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) { // 条目点击监听
                Intent startIntent = WebActivity.getStartIntent(getContext(), listData.get(position));
                startActivity(startIntent);
            }
        });
        return view;
    }

    public void refreshComplete() {
        mRecyclerView.refreshComplete();
    }

    public void loadMoreComplete() {
        mRecyclerView.loadMoreComplete();
    }

    public void loadNoMore() {
        mRecyclerView.setNoMore(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    //界面刷新
    public void refresh(List<Information> informations) {
        listData.clear();
        listData.addAll(informations);
        mRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();
    }
    //加载更多
    public void loadMore(List<Information> informations) {
        listData.addAll(informations);
        mRecyclerView.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }

}
