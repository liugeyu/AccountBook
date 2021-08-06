package com.geyu.db;

import com.geyu.base.BaseApplication;
import com.geyu.database.ben.AccountBook;
import com.geyu.rx.RxSchedulersHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class AccountBookManager {

    public static long getAccountBookId(){
        return 0;
    }


    public static Observable<List<AccountBook>> findAll(){
        return Observable.create((ObservableEmitter<List<AccountBook>> emitter) -> {
            List<AccountBook> accountBooks = BaseApplication.getmDaoSession().getAccountBookDao().queryBuilder().list();
            emitter.onNext(accountBooks);
            emitter.onComplete();
        }).compose(RxSchedulersHelper.applyIoTransformer());
    }
}
