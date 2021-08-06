package com.geyu.my.ui.contract

import com.geyu.base.BaseViewModel

class My_BackupContract {
    abstract class ViewModel: BaseViewModel() {
        abstract fun dataExport()
    }
    interface View{
        fun dataExport()
        fun manager()
    }
}