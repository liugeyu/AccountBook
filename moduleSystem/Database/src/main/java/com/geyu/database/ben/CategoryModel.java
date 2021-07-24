package com.geyu.database.ben;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CategoryModel {

    public static final int TYPE_EXPENSE = 0;

    public static final int TYPE_INCOME = 1;

    /** 分类记录 ID */
    @Id
    private Long id;

    /** 分类唯一不变名称 */
    private String uniqueName = "";

    /** 分类名称 */
    private String name = "";

    /** 图标 */
    private String icon = "";

    /** 排序 */
    private int order;

    /** 分类类型 */
    private int type;

    /** 用户 ID */
    private long accountId;

    /** 同步状态 */
    private int syncStatus;

    @Generated(hash = 196214191)
    public CategoryModel(Long id, String uniqueName, String name, String icon,
            int order, int type, long accountId, int syncStatus) {
        this.id = id;
        this.uniqueName = uniqueName;
        this.name = name;
        this.icon = icon;
        this.order = order;
        this.type = type;
        this.accountId = accountId;
        this.syncStatus = syncStatus;
    }

    @Generated(hash = 1421288718)
    public CategoryModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueName() {
        return this.uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
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
