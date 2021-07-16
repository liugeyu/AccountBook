package com.geyu.base;

import android.os.Bundle;

import com.geyu.utils.SystemBarTintManagerHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public abstract class BaseActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isDataBinding()) {
            initDataBinding(getLayoutId());
        } else {
            setContentView(getLayoutId());
        }
        initStarBar();
        initView();
        initData();
    }

    protected  void initStarBar() {
        if (isNeedStarBar()) {
            SystemBarTintManagerHelper.getInsatance().initStateBar(this, getStateBarColor(), isTransparentStateBar());
        }
    }

    protected  boolean isNeedStarBar() {
        return true;
    }

    protected  boolean isTransparentStateBar() {
        return false;
    }

    protected  int getStateBarColor() {

        return R.color.white;
    }


    protected void initDataBinding(int laytId){

    }
    protected  void initData() {
    }

    protected  void initView() {
    }

    protected abstract int getLayoutId();

    protected boolean isDataBinding(){
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
