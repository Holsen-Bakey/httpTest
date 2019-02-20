package com.pic.optimize.db;


import com.pic.optimize.DaoSession;
import com.pic.optimize.MessageContact;
import com.pic.optimize.MessageContactDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息通讯录数据库控制类
 *
 * @author gaoyoujian@babytree-inc.com
 * @since 2019/2/2
 */
public class MessageContactDbController {

    private static MessageContactDbController instance;
    private MessageContactDao dao;
    public static DaoSession daoSession;


    public static MessageContactDbController getInstance() {
        if (instance == null) {
            instance = new MessageContactDbController();
        }
        return instance;
    }

    public MessageContactDbController() {
        initGreenDao();
    }


    private void initGreenDao() {
       // MessageDaoOpenHelper.initDatabase();
        daoSession = MessageDaoOpenHelper.getDaoSession();
        dao = daoSession.getMessageContactDao();
    }

    /**
     * 数据新增或者更新
     *
     * @param bean
     */
    public long insert(MessageContact bean) {
        return dao.insertOrReplace(bean);
    }

    public void update(MessageContact bean) {
        dao.update(bean);
    }

    public void update(List<MessageContact> beanList) {
        dao.updateInTx(beanList);
    }

    public void delete(MessageContact bean) {
        dao.delete(bean);
    }


    public void delete(List<MessageContact> beanList) {
        dao.deleteInTx(beanList);
    }

    public void deleteAll() {
        dao.deleteAll();
    }

    public List<MessageContact> queryAll() {
        String uid = "uid";
        return dao.queryRaw("where my_user_id=?", uid);
    }

    public void deleteData(List<MessageContact> serverIdList) {
        List<MessageContact> list = this.queryAll();
        List<MessageContact> shouldDelete = new ArrayList();

        for (int i = 0; i < list.size(); ++i) {
            MessageContact bean = list.get(i);
            if (serverIdList.contains(bean)) {
                shouldDelete.add(bean);
            }
        }

        this.delete((List) shouldDelete);
    }

    public void insertOrReplace(List<MessageContact> bean) {
        this.dao.insertOrReplaceInTx(bean);
    }


    /**
     * 数据新增或者更新
     *
     * @param bean
     */
    public void insertAll(List<MessageContact> bean) {
        dao.insertOrReplaceInTx(bean);
    }

    public boolean isDatabaseEmpty() {
        List<MessageContact> list = queryAll();
        return list.size() == 0;
    }


}
