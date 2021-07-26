package com.geyu.database.ben;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 记录
 */
@Entity
public class Record {
    @Id
    private Long id;

    /** 账户 ID */
    private long accountId;
    /**
     * 账本id
     */
    private long accountBookId;

    /** 记录时间（UNIX TIME） */
    private long time;

    /** 分类唯一不变名称 */
    private String categoryUniqueName;

    /** 分类名称 */
    private String categoryName;

    /** 分类图标名称 */
    private String categoryIcon;

    /** 金额 分为单位*/
    private long amount;

    /** 备注 */
    private String desc;

    /** 同步 ID */
    private String syncId;

    /** 同步状态 */
    private int syncStatus;

    /** 记录类型 */
    private int type;

    @Generated(hash = 1201817292)
    public Record(Long id, long accountId, long accountBookId, long time,
            String categoryUniqueName, String categoryName, String categoryIcon,
            long amount, String desc, String syncId, int syncStatus, int type) {
        this.id = id;
        this.accountId = accountId;
        this.accountBookId = accountBookId;
        this.time = time;
        this.categoryUniqueName = categoryUniqueName;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.amount = amount;
        this.desc = desc;
        this.syncId = syncId;
        this.syncStatus = syncStatus;
        this.type = type;
    }

    @Generated(hash = 477726293)
    public Record() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getAccountBookId() {
        return this.accountBookId;
    }

    public void setAccountBookId(long accountBookId) {
        this.accountBookId = accountBookId;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCategoryUniqueName() {
        return this.categoryUniqueName;
    }

    public void setCategoryUniqueName(String categoryUniqueName) {
        this.categoryUniqueName = categoryUniqueName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return this.categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSyncId() {
        return this.syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
    }

    public int getSyncStatus() {
        return this.syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
