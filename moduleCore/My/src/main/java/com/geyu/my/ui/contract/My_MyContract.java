package com.geyu.my.ui.contract;

import com.geyu.base.BaseViewModel;

public class My_MyContract {

    public static abstract class ViewModel extends BaseViewModel {

    }

    public interface View{
        void backup();
        void about();
        void switchLanguage();
    }
}
