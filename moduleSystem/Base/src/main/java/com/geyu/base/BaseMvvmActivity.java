package com.geyu.base;

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
    }

    @Override
    protected boolean isDataBinding() {
        return true;
    }
}
