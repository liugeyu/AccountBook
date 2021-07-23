package com.geyu.home.ui.activity;

import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeActivityRecordEditBinding;
import com.geyu.home.ui.viewmodel.Home_RecordEditViewModel;
import com.geyu.utils.SystemBarTintManagerHelper;
import com.google.android.material.tabs.TabLayout;

@CreateViewModel(Home_RecordEditViewModel.class)
public class Home_RecordEditActivity extends BaseMvvmActivity<Home_RecordEditViewModel, HomeActivityRecordEditBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_record_edit;
    }


    @Override
    protected void initView() {
        super.initView();
        initTopBarAndTopPadding(mDataBinding.topBar);


    }

    @Override
    protected void initData() {
        super.initData();

    }
}
