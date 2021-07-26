package com.geyu.home.ui.viewmodel;


import com.geyu.callback.event.RecordChanager;
import com.geyu.database.ben.Record;
import com.geyu.db.RecordDaoManager;
import com.geyu.home.ui.contract.Home_HomeContract;
import com.geyu.rx.RxSchedulersHelper;
import com.geyu.utils.LLOG;
import com.geyu.utils.ToActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.disposables.Disposable;

public class Home_HomeViewModel extends Home_HomeContract.ViewMode {


    private MutableLiveData<List<Record>> homeDatas = new MutableLiveData();


    private void getHomeData(){
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


    @Override
    public void onCreate() {
        super.onCreate();
        getHomeData();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecrodChanager(RecordChanager event){
        getHomeData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
