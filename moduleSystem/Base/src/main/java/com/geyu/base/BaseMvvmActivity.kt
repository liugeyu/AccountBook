package com.geyu.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseMvvmActivity<VM:BaseViewModel,VDB :ViewDataBinding> : BaseActivity() {

    private lateinit var mDatabinding:VDB
    override fun initDataBinding(laytId: Int) {
        super.initDataBinding(laytId)
        mDatabinding = DataBindingUtil.setContentView(this,layoutId)

    }
}