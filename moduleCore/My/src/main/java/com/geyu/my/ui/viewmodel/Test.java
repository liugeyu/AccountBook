package com.geyu.my.ui.viewmodel;

import com.geyu.base.BaseViewModel;
import com.geyu.rx.LoadingObserver;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class Test {

    public void test(){
        Observable.just(1)
                .subscribe(new LoadingObserver<Integer>(new BaseViewModel()){
                    @Override
                    public void onNext(@NonNull Integer integer) {
                        super.onNext(integer);
                    }
                });
    }
}
