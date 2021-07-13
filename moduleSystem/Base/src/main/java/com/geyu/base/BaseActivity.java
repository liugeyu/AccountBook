package com.geyu.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isMvvm()) {
            initDataBinding(getLayoutId());
        } else {
            setContentView(getLayoutId());
        }
        initView();
        initData();
    }

    protected void initDataBinding(int laytId){

    }
    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected boolean isMvvm(){
        return false;
    }
}
