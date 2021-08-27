package com.geyu.home.ui.contract;

import com.geyu.base.BaseMvvmView;
import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.CategoryModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Home_categoryManagerFragmentContract {

    public static abstract class ViewModel extends BaseViewModel{

        public abstract void initData(int type);
        public abstract LiveData<List<CategoryModel>> getCategoryData();


    }

    public interface View extends BaseMvvmView<CategoryModel> {
        void add();
    }
}
