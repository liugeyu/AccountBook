package com.geyu.home.ui.contract

import com.geyu.base.BaseViewModel
import com.geyu.database.ben.AccountBook

class Home_AccountBookEditContract {

    abstract class ViewModel:BaseViewModel(){

    }

    interface View{

        fun save(accountBook:AccountBook)
        fun delete(accountBook: AccountBook)
    }
}