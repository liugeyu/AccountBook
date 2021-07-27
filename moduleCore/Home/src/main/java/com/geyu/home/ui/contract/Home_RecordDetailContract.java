package com.geyu.home.ui.contract;

import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.Record;

public class Home_RecordDetailContract {

    public static abstract class ViewModel extends BaseViewModel {
    }

    public interface View {
        void modification(Record record);
    }

}
