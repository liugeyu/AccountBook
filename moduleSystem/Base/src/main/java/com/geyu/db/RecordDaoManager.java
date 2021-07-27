package com.geyu.db;

import android.database.Cursor;

import com.geyu.base.BaseApplication;
import com.geyu.database.RecordDao;
import com.geyu.database.ben.Record;
import com.geyu.database.ben.RecordGroup;
import com.geyu.rx.RxSchedulersHelper;
import com.geyu.utils.LLOG;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class RecordDaoManager {

    public static void save(Record record) {
        BaseApplication.getmDaoSession().getRecordDao().insert(record);
    }

    public static void saveOrUpdate(Record record) {
        BaseApplication.getmDaoSession().getRecordDao().insertOrReplace(record);
    }

    /**
     * @param page 0 页开始
     * @param limit  分页大小
     * @return
     */
    public static List<Record> find(int page,int limit) {
        return BaseApplication.getmDaoSession().getRecordDao().queryBuilder().offset(page * limit).limit(limit).orderDesc(RecordDao.Properties.Id).list();
    }

    public static Observable<List<Record>> findRecords(int page,int limit) {
        return Observable.create(emitter -> {
            List<Record> data = find(page,limit);
            emitter.onNext(data);
            emitter.onComplete();
        });
    }


    public static Observable<List<RecordGroup>> queryGroupByCategory(long start,long ent, int type){
        return Observable.create((ObservableEmitter<List<RecordGroup>> emitter) -> {
            StringBuffer sb = getGroupSql();
            Cursor cursor = BaseApplication.getmDaoSession().getDatabase().rawQuery(sb.toString(),new String[]{start+"",ent+"", ""+ type});
            List<RecordGroup> results = new ArrayList<>();
            int uniqueName = cursor.getColumnIndexOrThrow(RecordGroup.columnUniqueName);
            int sumAmt = cursor.getColumnIndexOrThrow(RecordGroup.columnSumAmount);
            int name = cursor.getColumnIndexOrThrow(RecordGroup.columnName);
            int time = cursor.getColumnIndexOrThrow(RecordGroup.columnTime);
            int cType = cursor.getColumnIndexOrThrow(RecordGroup.columnType);
            while (cursor.moveToNext()){
                RecordGroup recordGroup = new RecordGroup();
                recordGroup.categoryName = cursor.getString(name);
                recordGroup.categoryUniqueName = cursor.getString(uniqueName);
                recordGroup.sumAmount = cursor.getLong(sumAmt);
                recordGroup.type = cursor.getInt(cType);
                recordGroup.time = cursor.getLong(time);
                results.add(recordGroup);
            }
            emitter.onNext(results);
            emitter.onComplete();
        }).compose(RxSchedulersHelper.applyIoTransformer());
    }

    private static StringBuffer getGroupSql (){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append(RecordGroup.columnCount +", ");
        sql.append(RecordGroup.columnSumAmount).append(", ");
        sql.append(RecordGroup.columnUniqueName).append(", ");
        sql.append(RecordGroup.columnName).append(", ");
        sql.append(RecordGroup.columnTime).append(", ");
        sql.append(RecordGroup.columnType).append(" ");

        sql.append("FROM ").append(RecordDao.TABLENAME).append(" ");

        sql.append("WHERE ").append(RecordDao.Properties.Time.columnName).append(" >= ?")
                .append(" AND ").append(RecordDao.Properties.Time.columnName).append(" <= ? ")
        .append("AND ").append(RecordDao.Properties.Type.columnName).append( " = ? ");


        sql.append("GROUP BY ").append(RecordDao.Properties.CategoryUniqueName.columnName);

        return sql;
    }



}
