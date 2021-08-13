package com.geyu.home.ui.viewmodel;

import android.text.TextUtils;
import android.widget.EditText;

import com.geyu.database.ben.Record;
import com.geyu.manager.db.RecordDaoManager;
import com.geyu.home.ui.contract.Home_SearchContract;
import com.geyu.home.util.RxSearchObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

public class Home_SearchViewModel extends Home_SearchContract.ViewModel {

    public MutableLiveData<List<Record>> searcheResult = new MutableLiveData<>();
    @Override
    public void starSearch(EditText editText) {
        Disposable disposable = RxSearchObservable.fromView(editText)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(text -> !TextUtils.isEmpty(text))
                .distinctUntilChanged()
                .switchMap(text -> RecordDaoManager.search(text).onErrorReturn(throwable -> new ArrayList<>()))
                .subscribe(records -> searcheResult.setValue(records), throwable -> {});
        addDisposable(disposable);
    }

    @Override
    public LiveData<List<Record>> getSearcher() {
        return searcheResult;
    }
}
