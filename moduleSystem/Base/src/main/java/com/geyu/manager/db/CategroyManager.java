package com.geyu.manager.db;

import com.geyu.base.BaseApplication;
import com.geyu.database.CategoryModelDao;
import com.geyu.database.ben.CategoryModel;
import com.geyu.rx.RxSchedulersHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class CategroyManager {


    public static Observable<List<CategoryModel>> findByType(int type){
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
        return BaseApplication.getmDaoSession().getCategoryModelDao().queryBuilder()
                .where(CategoryModelDao.Properties.Type.eq(type),CategoryModelDao.Properties.AccountBookId.eq(AccountBookManager.getAccountBookId()))
                .list();
    }

    public static void save(@NotNull CategoryModel categoryModel) {
        if (categoryModel != null) {
            BaseApplication.getmDaoSession().getCategoryModelDao().save(categoryModel);
        }
    }
    public static void saveOrUpdate(@NotNull CategoryModel categoryModel) {
        if (categoryModel != null) {
            BaseApplication.getmDaoSession().getCategoryModelDao().insertOrReplace(categoryModel);
        }
    }

//    public static void test(){
//        BaseApplication.getmDaoSession().getDatabase().execSQL();
//    }
}
