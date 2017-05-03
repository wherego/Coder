package com.peng_hongru.coder.presenter.activity;

import com.peng_hongru.coder.module.dao.DbHelper;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.presenter.base.BasePresenter;
import com.peng_hongru.coder.ui.activity.WebActivity;

/**
 * Created by 彭鸿儒 on 2017/4/30
 * 邮箱：peng_hongru@163.com
 */

public class WebPresenter extends BasePresenter {

    private WebActivity activity;

    public WebPresenter(WebActivity activity) {
        super();
        this.activity = activity;
    }


    public void refreshFloatActionBar(Information information) {
        activity.setFloatActionBar(DbHelper.contain(information));
    }

    public void collection(Information information) {
        if (!DbHelper.contain(information)) {
            try {
                DbHelper.getReadSession().getInformationDao().insert(information);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        refreshFloatActionBar(information);
    }
}
