package com.geyu.accountbook.ui.main.aty;

import com.geyu.accountbook.R;
import com.geyu.base.BaseActivity;
import com.geyu.database.ben.Record;
import com.geyu.db.RecordDaoManager;

public class MyTestActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_test;
    }


    @Override
    protected void initView() {
        super.initView();

        Record record = new Record();
        record.setAccountId(3232132);
        record.setAmount(111);
        record.setTime(System.currentTimeMillis());
        RecordDaoManager.save(record);
    }
}