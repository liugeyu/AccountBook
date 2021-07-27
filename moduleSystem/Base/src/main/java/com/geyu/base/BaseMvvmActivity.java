package com.geyu.base;

import com.geyu.utils.ToastUtil;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseMvvmActivity<VM extends BaseViewModel,VDB extends ViewDataBinding> extends BaseActivity{

    protected VDB mDataBinding;
    protected VM mViewModel;

    @Override
    protected void initDataBinding(int laytId) {

        mDataBinding = DataBindingUtil.setContentView(this,laytId);
        mViewModel = ViewModelFactoryImpl.getInstance().createViewModel(this);
        getLifecycle().addObserver(mViewModel);

        initErrMsg();
    }

    protected  void initErrMsg() {
        mViewModel.getErrMessage().observe(this, this::showErrMessage);
    }

    protected void showErrMessage(String errMsg){
        ToastUtil.showToast(errMsg);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected boolean isDataBinding() {
        return true;
    }
}
