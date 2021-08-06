package com.geyu.my.ui.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geyu.base.BaseViewModel
import com.geyu.my.ben.BackupFile

class My_BackupManagerContract {
    abstract class ViewModel :BaseViewModel(){
        abstract fun getDatas(): LiveData<List<BackupFile>>
        abstract fun loadMore()
        abstract fun getLoadMore():LiveData<List<BackupFile>>

        abstract fun delete(backupFile: BackupFile)

    }

    interface View{
        fun openDetail(backupFile: BackupFile)
        fun deleteItem(backupFile: BackupFile, position:Int)
    }
}