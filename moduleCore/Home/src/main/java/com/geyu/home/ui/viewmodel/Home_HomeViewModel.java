package com.geyu.home.ui.viewmodel;


import com.geyu.callback.event.RecordChanager;
import com.geyu.database.ben.Record;
import com.geyu.manager.db.RecordDaoManager;
import com.geyu.home.ui.contract.Home_HomeContract;
import com.geyu.rx.RxSchedulersHelper;
import com.geyu.utils.ErrHandler;
import com.geyu.utils.LLOG;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Home_HomeViewModel extends Home_HomeContract.ViewMode {


    private MutableLiveData<List<Record>> homeDatas = new MutableLiveData();
    private MutableLiveData<List<Record>> loadMore = new MutableLiveData();
    private int page = 0;
    private final int pageSize = 20;


    private void getHomeData(){
        page = 0;
        Disposable disposable = RecordDaoManager.findRecords(page,pageSize)
                .compose(RxSchedulersHelper.applyIoTransformer())
                .subscribe((rs) -> homeDatas.setValue(rs), throwable -> {
                    showMessage(ErrHandler.getErrMsg(throwable));
                });
        addDisposable(disposable);
    }

    public LiveData<List<Record>> getRecord() {
        return homeDatas;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LLOG.e("home on create");
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

    @Override
    public void refresh() {
        getHomeData();
    }

    @Override
    public void loadMore() {
        page ++;
        Disposable disposable = RecordDaoManager.findRecords(page,pageSize)
                .compose(RxSchedulersHelper.applyIoTransformer())
                .subscribe((rs) ->{
                    loadMore.setValue(rs);
                },throwable -> {
                    showMessage(ErrHandler.getErrMsg(throwable));
                });
        addDisposable(disposable);
    }

    @Override
    public MutableLiveData<List<Record>> getLoadMore() {
        return loadMore;
    }

    @Override
    public Observable<List<Record>> getRecords(Map<String, Object> data) {
        return RecordDaoManager.findRecords((Integer) data.get("page"),pageSize);
    }

}
