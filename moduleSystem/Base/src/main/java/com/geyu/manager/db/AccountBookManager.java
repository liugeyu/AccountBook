package com.geyu.manager.db;

import com.geyu.base.BaseApplication;
import com.geyu.database.AccountBookDao;
import com.geyu.database.ben.AccountBook;
import com.geyu.manager.SpManager;
import com.geyu.rx.RxSchedulersHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class AccountBookManager {

    public static long getAccountBookId(){
        return SpManager.AccountBooks.getSelectId();
    }

    public static AccountBook findAccountBook(){
        return BaseApplication.getmDaoSession().getAccountBookDao().queryBuilder().where(AccountBookDao.Properties.AccountBookId.eq(getAccountBookId())).unique();
    }


    public static Observable<List<AccountBook>> findAll(){
        return Observable.create((ObservableEmitter<List<AccountBook>> emitter) -> {
            List<AccountBook> accountBooks = BaseApplication.getmDaoSession().getAccountBookDao().queryBuilder().list();
            emitter.onNext(accountBooks);
            emitter.onComplete();
        }).compose(RxSchedulersHelper.applyIoTransformer());
    }

    public static long getAccountBookCount(){
        return BaseApplication.getmDaoSession().getAccountBookDao().queryBuilder().count();
    }

    public static void save(@Nullable AccountBook accountBook) {
        if (accountBook != null) {
            BaseApplication.getmDaoSession().getAccountBookDao().save(accountBook);
        }
    }

    public static void delete(@NotNull AccountBook accountBook) {
        BaseApplication.getmDaoSession().getAccountBookDao().delete(accountBook);
    }

    public static void saveOrUpdate(@NotNull AccountBook accountBook) {
        BaseApplication.getmDaoSession().getAccountBookDao().insertOrReplace(accountBook);
    }
}
