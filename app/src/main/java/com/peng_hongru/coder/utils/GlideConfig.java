package com.peng_hongru.coder.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpGlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import java.io.InputStream;


/**
 * glide功能定制
 * Created by 彭鸿儒 on 2016/7/12
 * 邮箱：peng_hongru@163.com
 */
public class GlideConfig extends OkHttpGlideModule {
    int diskSize = 1024 * 1024 * 100; // 100kb本地内存
    int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // 定义缓存大小和位置
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskSize));  // 内存中
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "cache", diskSize)); // sd卡中

        // 自定义内存和图片池大小
        builder.setMemoryCache(new LruResourceCache(memorySize)); // 自定义内存大小
        builder.setBitmapPool(new LruBitmapPool(memorySize)); // 自定义图片池大小

        // 定义图片格式
        // builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888); // 高质量
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565); // 默认(中等质量)
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());//将底层网络框架替换为okhttp
    }
}