package com.geyu.home.ui.viewmodel;


import com.geyu.database.ben.Record;
import com.geyu.db.RecordDaoManager;
import com.geyu.home.ui.contract.Home_HomeContract;
import com.geyu.rx.RxSchedulersHelper;
import com.geyu.utils.LLOG;
import com.geyu.utils.ToActivity;

import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.disposables.Disposable;

public class Home_HomeViewModel extends Home_HomeContract.ViewMode {


    private MutableLiveData<List<Record>> homeDatas = new MutableLiveData();


    public void getHomeData(){
        Disposable disposable = RecordDaoManager.findRecords(0,20)
                .compose(RxSchedulersHelper.applyIoTransformer())
                .subscribe((rs) ->{
                    homeDatas.setValue(rs);
                },throwable -> {
                });
        addDisposable(disposable);
    }

    public LiveData<List<Record>> getRecord(){
        return homeDatas;
    }


}
