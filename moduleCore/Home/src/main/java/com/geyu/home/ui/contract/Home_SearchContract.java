package com.geyu.home.ui.contract;

import android.widget.EditText;

import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.Record;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Home_SearchContract {

    public static abstract class ViewModel extends BaseViewModel {
        public abstract void starSearch(EditText editText);
        public abstract LiveData<List<Record>> getSearcher();
    }

    public interface View {
        void onItemClickListener(Record record,int position);
        void onBackActivity();
    }
}
