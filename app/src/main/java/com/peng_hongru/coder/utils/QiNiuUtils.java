package com.peng_hongru.coder.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.peng_hongru.coder.App;

/**
 * 七牛图片处理
 * Created by 彭鸿儒 on 2016/7/12
 * 邮箱：peng_hongru@163.com
 */

public class QiNiuUtils {
    private static QiNiuUtils qiNiu;

    private StringBuffer url;

    public static QiNiuUtils setUrl(String url) {
        if (qiNiu == null) {
            synchronized (QiNiuUtils.class) {
                if (qiNiu == null) {
                    qiNiu = new QiNiuUtils();
                }
            }
        }
        qiNiu.url = new StringBuffer(url);
        qiNiu.url.append("?");
        return qiNiu;
    }
    //图片瘦身
    public QiNiuUtils slim() {
        url.append("imageslim");
        return qiNiu;
    }
    //图片输出格式

    /**
     *
     * @param format @{Format.Jpg,Format.GIF,Format.PNG,Format.WEBP}
     * @return
     */
    public QiNiuUtils format(String format) {
        url.append("/interlace/");
        url.append(format);
        return qiNiu;
    }
    //图片格式类
    public interface Format {
        String JPG = "jpg";
        String GIF = "gif";
        String PNG = "pne";
        String WEBP = "webp";
    }


    /**
     * 图片质量
     * @param quality 默认75 ，取值范围 0-100
     * @param isSure 是否强制获得指定质量的图片
     * @return
     */
    public QiNiuUtils quality(int quality,boolean isSure) {
        url.append("/q/");
        if (quality < 0) {
            url.append(0);
        } else if (quality > 100) {
            url.append(100);
        } else {
            url.append(quality);
        }
        if (isSure) {
            url.append("!");
        }
        return qiNiu;
    }
    //渐进式显示
    public QiNiuUtils interlace(boolean enable) {
        url.append("/interlace/");
        if (enable) {
            url.append(1);
        } else {
            url.append(0);
        }
        return qiNiu;
    }

    /**
     *
     * @param width 图片宽 单位dp
     * @param height 图片长 单位dp
     * @return
     */
    public QiNiuUtils size(int width, int height) {
        url.append("/5/w/");
        url.append(dip2px(App.getAppContext(), width));
        url.append("/h/");
        url.append(dip2px(App.getAppContext(), height));
        return qiNiu;
    }

    public QiNiuUtils size(Activity activity, boolean isFullScreen) {
        if (isFullScreen) {
            int screenWidth  = activity.getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
            int screenHeight = activity.getWindowManager().getDefaultDisplay().getHeight();
            url.append("/5/w/");
            url.append(screenWidth + 50);
            url.append("/h/");
            url.append(screenHeight + 50);
        }
        return qiNiu;
    }

    /**
     * 无法在onCreate 方法中获得view 的宽高
     * @param view
     * @return
     */
    @Deprecated
    public QiNiuUtils size(View view) {
        url.append("/5/w/");
        url.append(view.getMeasuredWidth());
        url.append("/h/");
        url.append(view.getMeasuredHeight());
        Logger.d("width : " + view.getWidth() + " height : " + view.getHeight());
        return qiNiu;
    }

    //获得处理后的图片路径
    public String commit() {
        return qiNiu.url.toString();
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
