package com.geyu.accountbook.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.geyu.base.BaseViewModel
import com.geyu.utils.LLOG

public class MyTestViewModel: BaseViewModel() {

    // Two-way databinding, exposing MutableLiveData
    val title = MutableLiveData<String>()


     fun onClick(){
         title.value = "${System.currentTimeMillis()}"
         LLOG.e("${title.value}")
    }
}