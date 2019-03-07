package com.pic.optimize.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pic.optimize.DaoMaster;
import com.pic.optimize.fresco.PicApplication;
import com.pic.optimize.test.bean.DaoSession;
import com.pic.optimize.test.bean.QuestionInfoDao;

import org.greenrobot.greendao.database.Database;

public class MessageDaoOpenHelper extends DaoMaster.OpenHelper {

    private static DaoSession mDaoSession;
    private QuestionInfoDao dao;

    public MessageDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static void initDatabase() {
        MessageDaoOpenHelper helper = new MessageDaoOpenHelper(PicApplication.getContext(), "message_center.db", null);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        mDaoSession = daoMaster.newSession();
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.d("TAG","<<<<<oldVersion="+oldVersion+"newVersion="+newVersion);
        if (oldVersion == newVersion) {
            return;
        }
        switch (oldVersion) {
            case 1:
                DaoMaster.dropAllTables(db, true);
                DaoMaster.createAllTables(db, false);
            default:
                break;

        }
    }

}
