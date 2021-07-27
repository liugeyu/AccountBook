package com.geyu.home.ui.contract;

import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.Record;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class Home_HomeContract {

    public static abstract class ViewMode extends BaseViewModel{
        public abstract void refresh();
        public abstract void loadMore();
        public abstract MutableLiveData<List<Record>> getLoadMore();
    }

    public interface View{
        void addNewRecord();
        void onItemClick(Record itemData, int position);
    }
}
