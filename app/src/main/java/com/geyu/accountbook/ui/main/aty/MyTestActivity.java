package com.geyu.accountbook.ui.main.aty;

import com.geyu.accountbook.R;
import com.geyu.accountbook.databinding.ActivityMyTestBinding;
import com.geyu.accountbook.ui.main.viewmodel.MyTestViewModel;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;

@CreateViewModel(MyTestViewModel.class)
public class MyTestActivity extends BaseMvvmActivity<MyTestViewModel, ActivityMyTestBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_test;
    }


    @Override
    protected void initView() {
        super.initView();


        mDataBinding.setViewModel(mViewModel);
    }
}