package com.geyu.db;

import com.geyu.base.BaseApplication;
import com.geyu.database.CategoryModelDao;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.Record;
import com.geyu.rx.RxSchedulersHelper;

import java.util.List;

import io.reactivex.Observable;

public class CategroyManager {


    public static Observable<List<CategoryModel>> findAll(int type){
        return Observable.just(type)
                .map(integer -> find(type)).compose(RxSchedulersHelper.applyIoTransformer());

    }

    public static List<CategoryModel> find(int type) {
        return BaseApplication.getmDaoSession().getCategoryModelDao().queryBuilder().where(CategoryModelDao.Properties.Type.eq(type)).list();
    }
}
