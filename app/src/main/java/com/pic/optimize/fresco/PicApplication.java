package com.pic.optimize.fresco;

import android.app.Application;
import android.content.Context;
import com.pic.optimize.http.api.OkHttpUtil;

<<<<<<< HEAD
=======
import com.pic.optimize.db.MessageDaoOpenHelper;
import com.pic.optimize.db.QuestionDaoOpenHelper;
import com.pic.optimize.http.OkHttpUtil;
import com.pic.optimize.test.bean.CrashHandler;
>>>>>>> fix bug

public class PicApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CrashHandler.getInstance().init(this);
        OkHttpUtil.init();
<<<<<<< HEAD
=======
        MessageDaoOpenHelper.initDatabase();
        QuestionDaoOpenHelper.initDatabase();
>>>>>>> fix bug
    }

    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static String getResString(int resid) {
        return mContext.getString(resid);
    }

}
