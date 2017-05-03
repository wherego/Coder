package com.peng_hongru.coder.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.peng_hongru.coder.R;
import com.peng_hongru.coder.dagger.component.fragment.DaggerGirlsComponent;
import com.peng_hongru.coder.dagger.module.fragment.GirlsModule;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.presenter.fragment.GirlsPresenter;
import com.peng_hongru.coder.ui.activity.ImageActivity;
import com.peng_hongru.coder.ui.adapter.BaseAdapter;
import com.peng_hongru.coder.ui.adapter.GirlsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 彭鸿儒 on 2017/4/17.
 * 邮箱：peng_hongru@163.com
 */

public class GirlsFragment extends BaseFragment {
    @Inject
    public GirlsPresenter presenter;
    @InjectView(R.id.xrv_girls_container)
    XRecyclerView mRecyclerView;
    private GirlsAdapter mAdapter;
    private ArrayList<Information> listData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_girls, null);
        ButterKnife.inject(this, view);
        DaggerGirlsComponent component = (DaggerGirlsComponent) DaggerGirlsComponent.builder()
                .girlsModule(new GirlsModule(this))
                .build();
        component.in(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.loadData();
            }

            @Override
            public void onLoadMore() {
                try {
                    presenter.loadMore();
                } catch (Exception e) {
                    mRecyclerView.setNoMore(true);
                    e.printStackTrace();
                }
            }
        });
        listData = new ArrayList<>();
        mAdapter = new GirlsAdapter(listData, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent startIntent = ImageActivity.getStartIntent(getContext(),listData,position);
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


    public void refresh(List<Information> informations) {
        listData.clear();
        listData.addAll(informations);
        mRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();
    }

    public void loadMore(List<Information> informations) {
        listData.addAll(informations);
        mRecyclerView.loadMoreComplete();
        mAdapter.notifyDataSetChanged();
    }

}
