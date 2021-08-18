package com.geyu.home.ui.contract;

import com.geyu.base.BaseMvvmView;
import com.geyu.base.BaseViewModel;
import com.geyu.database.ben.AccountBook;

public class Home_CreateAccountBookContract {

    public static abstract class ViewModel extends BaseViewModel{
    }

    public interface View {
        void save(AccountBook accountBook);
    }
}
