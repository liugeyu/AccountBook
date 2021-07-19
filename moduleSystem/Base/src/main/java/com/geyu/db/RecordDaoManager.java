package com.geyu.db;

import com.geyu.base.BaseApplication;
import com.geyu.database.ben.Record;

public class RecordDaoManager {

    public static void save(Record record) {
        BaseApplication.getmDaoSession().getRecordDao().insert(record);
    }

}
