package com.pic.optimize.fresco;

import android.app.Application;
import android.content.Context;
import com.pic.optimize.http.api.OkHttpUtil;
import com.pic.optimize.db.MessageDaoOpenHelper;
import com.pic.optimize.db.QuestionDaoOpenHelper;
import com.pic.optimize.test.bean.CrashHandler;


public class PicApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CrashHandler.getInstance().init(this);
        OkHttpUtil.init();
        MessageDaoOpenHelper.initDatabase();
        QuestionDaoOpenHelper.initDatabase();
    }

    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static String getResString(int resid) {
        return mContext.getString(resid);
    }

}
