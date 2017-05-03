package com.peng_hongru.coder.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.peng_hongru.coder.R;

import java.lang.reflect.Field;

/**
 * Created by 彭鸿儒 on 2017/4/18.
 * 邮箱：peng_hongru@163.com
 */

public class BaseActivity extends AppCompatActivity {


    /**
     * 获取状态栏的高度
     * @return
     */
    public int getStatusBarHeight(){
        try
        {
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //弹土司
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }
    //使用自定义动画启动页面
    public void starActivityWithAnimator(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
    }

}
