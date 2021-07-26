package com.geyu.home.ui.contract;

import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.CategoryModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class Home_RecordEditContract {

    public interface View {
        void categoryItemClick(CategoryModel itemData,int itemPosition);
    }

    public static abstract class ViewModel extends BaseViewModel{

        public abstract void saveOrUpdateRecord(String amt, CategoryModel categoryModel);

        public abstract MutableLiveData<List<CategoryModel>> getCategoryData();
    }
}
