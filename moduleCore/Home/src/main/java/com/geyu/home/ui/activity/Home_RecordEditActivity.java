package com.geyu.home.ui.activity;


import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.callback.NumericKeypadConfirm;
import com.geyu.database.ben.Record;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeActivityRecordEditBinding;
import com.geyu.home.ui.viewmodel.Home_RecordEditViewModel;

@CreateViewModel(Home_RecordEditViewModel.class)
public class Home_RecordEditActivity extends BaseMvvmActivity<Home_RecordEditViewModel, HomeActivityRecordEditBinding> implements NumericKeypadConfirm {


    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_record_edit;
    }


    @Override
    protected void initView() {
        super.initView();
        initTopBarAndTopPadding(mDataBinding.topBar);
        mDataBinding.numberKeypad.setEditText(mDataBinding.tvAmount);

        mDataBinding.numberKeypad.setListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onConfirm() {
        mViewModel.saveOrUpdateRecord();
    }
}
