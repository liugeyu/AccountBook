package com.geyu.home.ui.contract;

import com.geyu.base.BaseViewModel;
public class Home_HomeContract {

    public static abstract class ViewMode extends BaseViewModel{
    }

    public interface View{
        void addNewRecord();
    }
}
