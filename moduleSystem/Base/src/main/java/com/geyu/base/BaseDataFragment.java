package com.geyu.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geyu.base.BaseFragment;
import com.geyu.base.BaseViewModel;
import com.geyu.base.ViewModelFactoryImpl;
import com.geyu.utils.ToastUtil;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseDataFragment<D extends Serializable,VM extends BaseViewModel,VDB extends ViewDataBinding> extends BaseFragment<D> {

    protected VM mViewModel;
    protected  VDB mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        mViewModel = ViewModelFactoryImpl.getInstance().createViewModel(this);
        getLifecycle().addObserver(mViewModel);
        mDataBinding.setLifecycleOwner(this);
        initErrMsg();
        return mDataBinding.getRoot();

    }
    protected  void initErrMsg() {
        mViewModel.getMessage().observe(getViewLifecycleOwner(), this::showErrMessage);
    }

    protected void showErrMessage(String errMsg){
        ToastUtil.showToast(errMsg);
    }
    protected abstract int getLayoutId();
}
