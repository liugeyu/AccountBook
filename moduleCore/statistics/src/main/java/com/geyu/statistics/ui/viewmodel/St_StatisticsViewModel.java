package com.geyu.statistics.ui.viewmodel;

import com.geyu.callback.event.RecordChanager;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.RecordGroup;
import com.geyu.db.RecordDaoManager;
import com.geyu.statistics.ui.contract.St_statisticsContract;
import com.geyu.utils.LLOG;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

public class St_StatisticsViewModel extends St_statisticsContract.ViewModel {

    private MutableLiveData<List<RecordGroup>> recordGroup = new MutableLiveData<>();
    @Override
    public MutableLiveData<List<RecordGroup>> getRecordGroup() {
        return recordGroup;
    }


    private void initData() {
        Disposable disposable = RecordDaoManager.queryGroupByCategory(0,System.currentTimeMillis(), CategoryModel.TYPE_EXPENSE)
                .subscribe(recordGroups -> {
                    LLOG.e(recordGroups.toString());
                    recordGroup.postValue(recordGroups);
                },throwable -> {
                    showMessage(throwable.getMessage());
                });
        addDisposable(disposable);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        initData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecrodChanager(RecordChanager event){
        initData();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
