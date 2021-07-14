package com.geyu.base;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {


    @Override
    protected void onCleared() {
        super.onCleared();

        Log.e("lgy","onCleared");
    }
}
