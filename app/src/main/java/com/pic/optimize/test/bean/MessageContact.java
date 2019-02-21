package com.pic.optimize.test.bean;


import android.support.annotation.Keep;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 消息通讯录数据库 model
 *
 * @author gaoyoujian@babytree-inc.com
 * @since 2019/2/2 下午3:36
 */
@Keep
@Entity(nameInDb = "MessageContactBean", indexes = {
        @Index(value = "my_user_id DESC,  user_id DESC", unique = true)})
public class MessageContact {

    @Id(autoincrement = true)
    public long id;

    public String my_user_id;

    public String user_id;

    public String avatar;

    public String nickname;
    /**
     * 记录状态：0-删除、1-正常
     */
    public String status;

    public String daren_type;

    /**
     * 普通:1,铁牌:2,铜牌:3,银牌:4
     */
    public String level;


    /**
     * 孕龄
     */
    public String desc;


@Generated(hash = 1113520529)
public MessageContact(long id, String my_user_id, String user_id, String avatar,
        String nickname, String status, String daren_type, String level,
        String desc) {
    this.id = id;
    this.my_user_id = my_user_id;
    this.user_id = user_id;
    this.avatar = avatar;
    this.nickname = nickname;
    this.status = status;
    this.daren_type = daren_type;
    this.level = level;
    this.desc = desc;
}


@Generated(hash = 1248771935)
public MessageContact() {
}


public long getId() {
    return this.id;
}


public void setId(long id) {
    this.id = id;
}


public String getMy_user_id() {
    return this.my_user_id;
}


public void setMy_user_id(String my_user_id) {
    this.my_user_id = my_user_id;
}


public String getUser_id() {
    return this.user_id;
}


public void setUser_id(String user_id) {
    this.user_id = user_id;
}


public String getAvatar() {
    return this.avatar;
}


public void setAvatar(String avatar) {
    this.avatar = avatar;
}


public String getNickname() {
    return this.nickname;
}


public void setNickname(String nickname) {
    this.nickname = nickname;
}


public String getStatus() {
    return this.status;
}


public void setStatus(String status) {
    this.status = status;
}


public String getDaren_type() {
    return this.daren_type;
}


public void setDaren_type(String daren_type) {
    this.daren_type = daren_type;
}


public String getLevel() {
    return this.level;
}


public void setLevel(String level) {
    this.level = level;
}


public String getDesc() {
    return this.desc;
}


public void setDesc(String desc) {
    this.desc = desc;
}




}
