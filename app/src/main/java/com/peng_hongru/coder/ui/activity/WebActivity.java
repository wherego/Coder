package com.peng_hongru.coder.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.peng_hongru.coder.R;
import com.peng_hongru.coder.dagger.component.activity.DaggerWebComponent;
import com.peng_hongru.coder.dagger.module.activity.WebModule;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.presenter.activity.WebPresenter;
import com.peng_hongru.coder.utils.WebViewManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WebActivity extends BaseActivity implements WebViewManager.OnLoadingListener, View.OnClickListener {

    @Inject
    public WebPresenter presenter;

    private static final String DATA_KEY = "cllection";

    @InjectView(R.id.web_client)
    WebView webClient;
    @InjectView(R.id.web_loading)
    ProgressBar webLoading;
    @InjectView(R.id.add)
    FloatingActionButton add;
    private WebViewManager manager;
    private Information information;

    //自定义跳转intent
    public static Intent getStartIntent(Context context, Information information) {
        Intent intent = new Intent(context, WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DATA_KEY, information);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.inject(this);
        DaggerWebComponent component = (DaggerWebComponent) DaggerWebComponent.builder()
                .webModule(new WebModule(this))
                .build();
        component.in(this);
        // 获得传递来的数据
        information = getIntent().getParcelableExtra(DATA_KEY);
        // webview设置
        manager = new WebViewManager(webClient);
        manager.disableJump()
                .enableAdaptive()
                .enableZoom()
                .enableJavaScript()
                .enableJavaScriptOpenWindowsAutomatically()
                .setOnLoadingListener(this)
                .loadUrl(information.getUrl());
        //设置floatactionbar
        presenter.refreshFloatActionBar(information);
        add.setOnClickListener(this);
    }

    public void setFloatActionBar(boolean has) {
        if (has) {
            add.setImageDrawable(getResources().getDrawable(R.drawable.ic_finish));
        } else {
            add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add));
        }
    }

    //网页开始加载
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        webLoading.setVisibility(View.VISIBLE);
    }

    //网页加载中
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        webLoading.setProgress(newProgress);
    }

    //网页加载完成
    @Override
    public void onPageFinished(WebView view, String url) {
        webLoading.setVisibility(View.GONE);
    }

    //返回键监听
    @Override
    public void onBackPressed() {
        if (!manager.goBack()) {
            super.onBackPressed();
        }
    }

    //floatactionbar点击监听
    @Override
    public void onClick(View v) {
        presenter.collection(information);
    }
}
