package com.geyu.statistics.ui.contract;

import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.RecordGroup;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class St_statisticsContract {

    public static abstract class ViewModel extends BaseViewModel {
        public abstract MutableLiveData<List<RecordGroup>> getRecordGroup();
    }
}
