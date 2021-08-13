package com.geyu.manager.db;

import com.geyu.base.BaseApplication;
import com.geyu.database.ben.Account;
import com.geyu.rx.RxSchedulersHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class AccountManager {
    /**
     * 获取账户id
     * @return
     */
    public static long getAccountId(){
        return 0;
    }


    public static Observable<List<Account>> findAll(){
        return Observable.create((ObservableEmitter<List<Account>> emitter) -> {
            List<Account> accounts = BaseApplication.getmDaoSession().getAccountDao().queryBuilder().list();
            emitter.onNext(accounts);
            emitter.onComplete();
        }).compose(RxSchedulersHelper.applyIoTransformer());
    }
}
