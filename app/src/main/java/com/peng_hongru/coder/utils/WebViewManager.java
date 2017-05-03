/*
 * Created by phr on 2016/12/11.
 */

package com.peng_hongru.coder.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * WebView管理器，提供常用设置
 * Created by 彭鸿儒 on 2016/7/12
 * 邮箱：peng_hongru@163.com
 */
public class WebViewManager {
    private WebView webView;
    private WebSettings webSettings;

    public WebViewManager(WebView webView) {
        this.webView = webView;
        webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    }

    /**
     * 网页状态监听
     * @param listener
     * @return
     */
    public WebViewManager setOnLoadingListener(@NonNull final OnLoadingListener listener) {
        webView.setWebViewClient(new WebViewClient() {
            //页面开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                listener.onPageStarted(view, url, favicon);
            }

            //页面加载结束
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                listener.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            //页面加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                listener.onProgressChanged(view, newProgress);
            }
        });
        return this;
    }

    public interface OnLoadingListener {
        //页面开始加载
        void onPageStarted(WebView view, String url, Bitmap favicon);

        //页面加载进度
        void onProgressChanged(WebView view, int newProgress);

        //页面加载结束
        void onPageFinished(WebView view, String url);
    }


    /**
     * 加载网页
     *
     * @param url
     * @return
     */
    public WebViewManager loadUrl(String url) {
        webView.loadUrl(url);
        return this;
    }

    /**
     * 禁止跳转浏览器
     *
     * @return
     */
    public WebViewManager disableJump() {
        webView.setWebViewClient(new WebViewClient() { // 禁止跳转至浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        return this;
    }

    /**
     * 开启自适应功能
     */
    public WebViewManager enableAdaptive() {
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        return this;
    }

    /**
     * 禁用自适应功能
     */
    public WebViewManager disableAdaptive() {
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        return this;
    }

    /**
     * 开启缩放功能
     */
    public WebViewManager enableZoom() {
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        return this;
    }

    /**
     * 禁用缩放功能
     */
    public WebViewManager disableZoom() {
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(false);
        webSettings.setBuiltInZoomControls(false);
        return this;
    }

    /**
     * 开启JavaScript
     */
    @SuppressLint("SetJavaScriptEnabled")
    public WebViewManager enableJavaScript() {
        webSettings.setJavaScriptEnabled(true);
        return this;
    }

    /**
     * 禁用JavaScript
     */
    public WebViewManager disableJavaScript() {
        webSettings.setJavaScriptEnabled(false);
        return this;
    }

    /**
     * 开启JavaScript自动弹窗
     */
    public WebViewManager enableJavaScriptOpenWindowsAutomatically() {
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        return this;
    }

    /**
     * 禁用JavaScript自动弹窗
     */
    public WebViewManager disableJavaScriptOpenWindowsAutomatically() {
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        return this;
    }

    /**
     * 返回
     *
     * @return true：已经返回，false：到头了没法返回了
     */
    public boolean goBack() {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            return false;
        }
    }
}