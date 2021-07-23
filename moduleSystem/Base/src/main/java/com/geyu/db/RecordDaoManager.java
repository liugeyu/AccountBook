package com.geyu.db;

import com.geyu.base.BaseApplication;
import com.geyu.database.ben.Record;

import java.util.List;

import io.reactivex.Observable;

public class RecordDaoManager {

    public static void save(Record record) {
        BaseApplication.getmDaoSession().getRecordDao().insert(record);
    }


    /**
     *
     * @param page 0 页开始
     * @param limit  分页大小
     * @return
     */
    public static List<Record> find(int page,int limit) {
        return BaseApplication.getmDaoSession().getRecordDao().queryBuilder().offset(page * limit).limit(limit).list();
    }

    public static Observable<List<Record>> findRecords(int page,int limit) {
        return Observable.just(find(page,limit));
    }
}
