package com.geyu.home.ui.contract

import androidx.lifecycle.LiveData
import com.geyu.base.BaseMvvmView
import com.geyu.base.BaseViewModel
import com.geyu.database.ben.CategoryModel

class Home_AddCategoryContract {

    abstract class ViewModel:BaseViewModel(){

        abstract fun loadData(type:Int)

        abstract fun getData():LiveData<List<CategoryModel>>
    }

    interface View:BaseMvvmView<CategoryModel>{
        fun add()
    }
}