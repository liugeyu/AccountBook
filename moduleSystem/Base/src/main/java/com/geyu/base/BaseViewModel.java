package com.geyu.base;

import android.util.Log;

import com.geyu.utils.LLOG;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel implements LifecycleObserver {
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<String> errMessage = new MutableLiveData<>();

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    protected void showErrMessage(String msg) {
        errMessage.postValue(msg);
    }
    public LiveData<String> getErrMessage(){
        return errMessage;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        LLOG.e("view model onCreate"+this.getClass().getSimpleName());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){}



    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        LLOG.e("view model onDestroy:"+this.getClass().getSimpleName());
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
