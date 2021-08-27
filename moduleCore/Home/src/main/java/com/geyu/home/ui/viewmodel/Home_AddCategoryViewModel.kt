package com.geyu.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geyu.database.ben.CategoryItem
import com.geyu.database.ben.CategoryModel
import com.geyu.help.CategoryInitData
import com.geyu.home.ui.contract.Home_AddCategoryContract
import com.geyu.manager.db.AccountBookManager

class Home_AddCategoryViewModel: Home_AddCategoryContract.ViewModel() {


    val datas = MutableLiveData<List<CategoryModel>>()


    override fun loadData(type: Int) {

        var categoryItems = ArrayList<CategoryItem>()
        categoryItems.addAll(CategoryInitData.getIncomeInitData())
        categoryItems.addAll(CategoryInitData.getExpenseInitData())
        var result = ArrayList<CategoryModel>()
        var accbookId = AccountBookManager.getAccountBookId()
        categoryItems?.forEach {
            var model = CategoryModel()
            model.type = type
            model.icon = it.icon
            model.accountBookId = accbookId
            result.add(model)
        }
        datas.value = result
    }

    override fun getData(): LiveData<List<CategoryModel>> {
        return datas
    }

}