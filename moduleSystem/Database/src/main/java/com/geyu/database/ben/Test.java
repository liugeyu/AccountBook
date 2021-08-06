package com.geyu.database.ben;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

@Entity
public class Test {

    @Id
    private Long id;
    
    private String time;


    private String msg2;
    private String msg;

    private Date date;

    @Convert(converter = NoteTypeConverter.class, columnType = String.class)
    private NoteType type;
    
    @Convert(converter = NoteTypeConverter.class, columnType = String.class)
    private NoteType type2;

    private int testInt;

    @Generated(hash = 1564583691)
    public Test(Long id, String time, String msg2, String msg, Date date,
            NoteType type, NoteType type2, int testInt) {
        this.id = id;
        this.time = time;
        this.msg2 = msg2;
        this.msg = msg;
        this.date = date;
        this.type = type;
        this.type2 = type2;
        this.testInt = testInt;
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

    public String getMsg2() {
        return this.msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NoteType getType() {
        return this.type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }

    public NoteType getType2() {
        return this.type2;
    }

    public void setType2(NoteType type2) {
        this.type2 = type2;
    }

    public int getTestInt() {
        return this.testInt;
    }

    public void setTestInt(int testInt) {
        this.testInt = testInt;
    }
}
