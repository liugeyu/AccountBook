package com.geyu.home.ui.contract;

import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.Record;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class Home_RecordEditFragmentContract {
    public static abstract class ViewModel extends BaseViewModel{

        public abstract void saveOrUpdateRecord(String amt, CategoryModel categoryModel, Record oldRecord,String remark);

        public abstract void initCategory(int type);
        public abstract MutableLiveData<List<CategoryModel>> getCategoryData();
    }

    public interface View{
        void categoryItemClick(CategoryModel itemData,int itemPosition);
    }
}
