package com.geyu.database.ben;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Account {
    @Id
    private Long id;

    /** 账户 名称 */
    private String accountName;

    /** 记录时间（UNIX TIME） */
    private long createTime;

    /** 账户 ID */
    private long accountId;

    /** 同步状态 */
    private int syncStatus;

    @Generated(hash = 1472066261)
    public Account(Long id, String accountName, long createTime, long accountId,
            int syncStatus) {
        this.id = id;
        this.accountName = accountName;
        this.createTime = createTime;
        this.accountId = accountId;
        this.syncStatus = syncStatus;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getSyncStatus() {
        return this.syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }
}
