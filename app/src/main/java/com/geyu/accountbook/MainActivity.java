package com.geyu.accountbook;



import com.geyu.accountbook.databinding.ActivityMainBinding;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;

@CreateViewModel(MainactivityViewModel.class)
public class MainActivity extends BaseMvvmActivity<MainactivityViewModel, ActivityMainBinding> {



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mViewModel.test();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}