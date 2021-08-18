package com.geyu.home.ui.contract;

import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.Record;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;

public class Home_HomeContract {

    public static abstract class ViewMode extends BaseViewModel{
        public abstract void refresh();
        public abstract void loadMore();
        public abstract MutableLiveData<List<Record>> getLoadMore();

        public abstract Observable<List<Record> > getRecords(Map<String,Object> data);
    }

    public interface View{
        void addNewRecord();
        void onItemClick(Record itemData, int position);
        void toSearch();
        void selectAccountBook();
    }

    //
}
