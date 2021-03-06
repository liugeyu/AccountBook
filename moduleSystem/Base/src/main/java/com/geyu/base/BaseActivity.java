package com.geyu.base;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.geyu.utils.ScreenManager;
import com.geyu.utils.SystemBarTintManagerHelper;
import com.geyu.utils.language.MultiLanguages;
import com.geyu.utils.swipeWindowHelper.SwipeWindowHelper;
import com.geyu.view.TopBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public abstract class BaseActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    private SwipeWindowHelper swipeWindowHelper;

    private TopBar topBar;

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguages.attach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenManager.getInstance().add(this);
        if (isDataBinding()) {
            initDataBinding(getLayoutId());
        } else {
            setContentView(getLayoutId());
        }
        initStarBar();
        initView();
        initData();
    }

    protected void initTopBar(TopBar topBar){
        topBar.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
    }

    protected void initTopBarAndTopPadding(TopBar topBar){
        SystemBarTintManagerHelper.getInsatance().titleBarPaddingTop(topBar);
        initTopBar(topBar);
    }



    protected void onBack(){
        finish();
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


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isSupportSlideBack()) {
            return super.dispatchTouchEvent(ev);
        }
        if (swipeWindowHelper == null) {
            swipeWindowHelper = new SwipeWindowHelper(getWindow());
        }
        return swipeWindowHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }
    /**
     * ????????????????????????
     *
     * @return
     */
    protected boolean isSupportSlideBack() {
        return true;
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
        ScreenManager.getInstance().remove(this);
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
