package com.pic.optimize;

import android.support.annotation.Keep;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Keep
@Entity(nameInDb = "CardBean")
public class CardBean {

    @Id
    public String id;

    @Generated(hash = 1607881921)
    public CardBean(String id) {
        this.id = id;
    }

    @Generated(hash = 516506924)
    public CardBean() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
