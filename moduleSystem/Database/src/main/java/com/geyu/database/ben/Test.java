package com.geyu.database.ben;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Test {

    @Id
    private Long id;
    
    private String time;

    private String msg;

    @Generated(hash = 1379299420)
    public Test(Long id, String time, String msg) {
        this.id = id;
        this.time = time;
        this.msg = msg;
    }

    @Generated(hash = 372557997)
    public Test() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
