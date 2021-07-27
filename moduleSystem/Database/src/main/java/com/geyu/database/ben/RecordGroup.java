package com.geyu.database.ben;

import com.geyu.database.RecordDao;

public class RecordGroup {

    public static final String columnCount = "COUNT(*)";
    public static final String columnSumAmount = "SUM("+ RecordDao.Properties.Amount.columnName+")";
    public static final String columnUniqueName = RecordDao.Properties.CategoryUniqueName.columnName;
    public static final String columnName = RecordDao.Properties.CategoryName.columnName;
    public static final String columnTime = RecordDao.Properties.Time.columnName;
    public static final String columnType = RecordDao.Properties.Type.columnName;



    public String categoryUniqueName;
    public String categoryName;
    public long time;
    public long sumAmount;
    public int type;


    @Override
    public String toString() {
        return "RecordGroup{" +
                "categoryUniqueName='" + categoryUniqueName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", time=" + time +
                ", sumAmount=" + sumAmount +
                ", type=" + type +
                '}';
    }
}
