package com.peng_hongru.coder.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.orhanobut.logger.Logger;
import com.peng_hongru.coder.R;
import com.peng_hongru.coder.ui.fragment.BaseFragment;
import com.peng_hongru.coder.ui.fragment.GirlsFragment;
import com.peng_hongru.coder.ui.fragment.InformationFragment;
import com.peng_hongru.coder.ui.fragment.RestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @InjectView(R.id.fl_main_container)
    FrameLayout mFlMainContainer;
    @InjectView(R.id.user_view)
    NavigationView userView;

    private List<BaseFragment> mFragmentList; // 界面内容集合
    // 底部菜单监听器
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_girls:
                    replaceFragment(mFragmentList.get(0));
                    return true;
                case R.id.navigation_information:
                    replaceFragment(mFragmentList.get(1));
                    return true;
                case R.id.navigation_rest:
                    replaceFragment(mFragmentList.get(2));
                    return true;
            }
            return false;
        }

    };
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initFragmentList();
        replaceFragment(mFragmentList.get(0));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //侧边栏初始化
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.user_view);
        navigationView.setNavigationItemSelectedListener(this);

        //toolbar返回键点击监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    //切换内容
    private void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(mFlMainContainer.getId(), fragment)
                .commit();
    }

    //界面内容初始化
    private void initFragmentList() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new GirlsFragment());
        mFragmentList.add(new InformationFragment());
        mFragmentList.add(new RestFragment());
    }

    //返回键监听
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_web:
                intent = CollectionActivity.getStartIntent(this, CollectionActivity.Type.Web);
                startActivity(intent);
                break;
            case R.id.nav_girls:
                Logger.d("查看收藏的妹子");
                intent = CollectionActivity.getStartIntent(this, CollectionActivity.Type.Girls);
                startActivity(intent);
                break;
            case R.id.nav_video:
                intent = CollectionActivity.getStartIntent(this, CollectionActivity.Type.Video);
                startActivity(intent);
                break;
            case R.id.nav_setting:
                Intent startIntent = SettingsActivity.getStartIntent(this);
                startActivity(startIntent);
                break;
        }
        return false;
    }


}
