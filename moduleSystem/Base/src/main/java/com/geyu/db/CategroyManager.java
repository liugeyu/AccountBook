package com.geyu.db;

import com.geyu.base.BaseApplication;
import com.geyu.database.CategoryModelDao;
import com.geyu.database.RecordDao;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.Record;
import com.geyu.rx.RxSchedulersHelper;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class CategroyManager {


    public static Observable<List<CategoryModel>> findAll(int type){
        return Observable.just(type)
                .map(integer -> find(type)).compose(RxSchedulersHelper.applyIoTransformer());

    }

    public static Observable<List<CategoryModel>> findAll(){
        return Observable.create((ObservableEmitter<List<CategoryModel>> emitter) -> {
            List<CategoryModel> data =  BaseApplication.getmDaoSession().getCategoryModelDao().queryBuilder().list();
            emitter.onNext(data);
            emitter.onComplete();
        }).compose(RxSchedulersHelper.applyIoTransformer());
    }

    public static List<CategoryModel> find(int type) {
        return BaseApplication.getmDaoSession().getCategoryModelDao().queryBuilder().where(CategoryModelDao.Properties.Type.eq(type)).list();
    }

//    public static void test(){
//        BaseApplication.getmDaoSession().getDatabase().execSQL();
//    }
}
