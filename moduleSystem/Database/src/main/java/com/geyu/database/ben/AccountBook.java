package com.geyu.database.ben;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * 账本
 */
@Entity
public class AccountBook implements Serializable {
    @Transient
    private static final long serialVersionUID = -4683833882623657815L;
    @Transient
    public boolean isEdit;

    @Id
    private Long id;

    /** 记录时间（UNIX TIME） */
    private long createTime;

    /** 账本 名称 */
    private String accountBookName;

    /** 账本 ID */
    @Unique
    private long accountBookId;

    /** 同步状态 */
    private int syncStatus;

    @Generated(hash = 1365636050)
    public AccountBook(Long id, long createTime, String accountBookName,
            long accountBookId, int syncStatus) {
        this.id = id;
        this.createTime = createTime;
        this.accountBookName = accountBookName;
        this.accountBookId = accountBookId;
        this.syncStatus = syncStatus;
    }

    @Generated(hash = 1809087649)
    public AccountBook() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getAccountBookName() {
        return this.accountBookName;
    }

    public void setAccountBookName(String accountBookName) {
        this.accountBookName = accountBookName;
    }

    public long getAccountBookId() {
        return this.accountBookId;
    }

    public void setAccountBookId(long accountBookId) {
        this.accountBookId = accountBookId;
    }

    public int getSyncStatus() {
        return this.syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }
}
