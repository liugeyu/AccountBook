package com.geyu.home.ui.activity;

import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.database.ben.Record;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeActivityRecordDetailBinding;
import com.geyu.home.ui.contract.Home_RecordDetailContract;
import com.geyu.home.ui.viewmodel.Home_RecordDetailViewModel;
import com.geyu.utils.ToActivity;

@CreateViewModel(Home_RecordDetailViewModel.class)
public class Home_recordDetailActivity extends BaseMvvmActivity<Home_RecordDetailViewModel, HomeActivityRecordDetailBinding>  implements Home_RecordDetailContract.View {
    private Record record;
    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_record_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBarAndTopPadding(mDataBinding.topbar);

    }

    @Override
    protected void initData() {
        super.initData();
        record = (Record) getIntent().getSerializableExtra(ToActivity.serializabkeKey);
        mDataBinding.setItemData(record);
        mDataBinding.setView(this);
    }

    @Override
    public void modification(Record record) {
        ToActivity.toActivity(this,Home_RecordEditActivity.class,record);
        finish();
    }
}
