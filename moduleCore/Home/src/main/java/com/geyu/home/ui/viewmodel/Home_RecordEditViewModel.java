package com.geyu.home.ui.viewmodel;

import com.geyu.database.ben.Record;
import com.geyu.db.RecordDaoManager;
import com.geyu.home.ui.contract.Home_RecordEditContract;

public class Home_RecordEditViewModel extends Home_RecordEditContract.ViewModel {

    @Override
    public void saveOrUpdateRecord() {
        Record record = new Record();
        record.setAccountId(111);
        record.setAmount(111);
        RecordDaoManager.save(record);
    }
}
