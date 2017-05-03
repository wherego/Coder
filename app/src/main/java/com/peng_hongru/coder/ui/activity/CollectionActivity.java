package com.peng_hongru.coder.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.peng_hongru.coder.R;
import com.peng_hongru.coder.module.dao.DbHelper;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.ui.adapter.BaseAdapter;
import com.peng_hongru.coder.ui.adapter.GirlsAdapter;
import com.peng_hongru.coder.ui.adapter.InformationAdapter;
import com.peng_hongru.coder.ui.adapter.RestAdapter;
import com.peng_hongru.greendao.dao.InformationDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class CollectionActivity extends BaseActivity {


    private static String TYPE = "type";
    @InjectView(R.id.collection_content)
    XRecyclerView mRecyclerView;
    private String queryFiled;
    private BaseAdapter adapter;
    private ArrayList<Information> listDatas;
    private RecyclerView.LayoutManager layoutManager;

    public interface Type {
        int Girls = 0;
        int Web = 1;
        int Video = 2;
    }


    public static Intent getStartIntent(Context context, int type) {
        Intent intent = new Intent(context, CollectionActivity.class);
        intent.putExtra(TYPE, type);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.inject(this);
        int type = getIntent().getIntExtra(TYPE, Type.Girls);
        listDatas = new ArrayList<>();
        switch (type) {
            case Type.Girls:
                queryFiled = "福利";
                layoutManager = new GridLayoutManager(this, 2);
                adapter = new GirlsAdapter(listDatas, this);
                break;
            case Type.Web:
                queryFiled = "Android";
                layoutManager = new LinearLayoutManager(this);
                adapter = new InformationAdapter(listDatas, this);
                break;
            case Type.Video:
                queryFiled = "休息视频";
                layoutManager = new LinearLayoutManager(this);
                adapter = new RestAdapter(listDatas, this);
                break;
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                listDatas.clear();
                listDatas.addAll(getInfomation());
                mRecyclerView.refreshComplete();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {

            }
        });

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.refresh();
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent startIntent = ImageActivity.getStartIntent(CollectionActivity.this, listDatas,position);
                startActivity(startIntent);
            }
        });
    }


    private ArrayList<Information> getInfomation() {
        ArrayList<Information> informations = new ArrayList<>();
        List<Information> list = DbHelper.getReadSession().queryBuilder(Information.class)
                .where(InformationDao.Properties.Type.eq(queryFiled))
                .list();
        informations.addAll(list);
        return informations;
    }

}
