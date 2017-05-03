package com.peng_hongru.coder.module.dao;

import com.peng_hongru.coder.App;
import com.peng_hongru.coder.module.net.bean.Information;
import com.peng_hongru.coder.utils.C;
import com.peng_hongru.greendao.dao.DaoMaster;
import com.peng_hongru.greendao.dao.DaoSession;
import com.peng_hongru.greendao.dao.InformationDao;

import java.util.List;

/**
 * Created by 彭鸿儒 on 2017/5/2
 * 邮箱：peng_hongru@163.com
 */

public class DbHelper {

    private static DaoMaster.DevOpenHelper openHelper;

    public static DaoSession getWriteSession() {
        if (openHelper == null) {
            synchronized (DbHelper.class) {
                if (openHelper == null) {
                    openHelper = new DaoMaster.DevOpenHelper(App.getAppContext(), C.Config.DB_NAME, null);
                }
            }
        }
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        return daoMaster.newSession();
    }

    public static DaoSession getReadSession() {
        if (openHelper == null) {
            synchronized (DbHelper.class) {
                if (openHelper == null) {
                    openHelper = new DaoMaster.DevOpenHelper(App.getAppContext(), C.Config.DB_NAME, null);
                }
            }
        }
        DaoMaster daoMaster = new DaoMaster(openHelper.getReadableDatabase());
        return daoMaster.newSession();
    }



    public static boolean contain(Information information) {
        InformationDao informationDao = getReadSession().getInformationDao();
        List<Information> list = informationDao.queryBuilder()
                .where(InformationDao.Properties.Url.eq(information.getUrl()))
                .list();
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
