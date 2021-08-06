package com.geyu.my.ui.aty

import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.my.R
import com.geyu.my.databinding.MyActivityBackupBinding
import com.geyu.my.ui.contract.My_BackupContract
import com.geyu.my.ui.viewmodel.My_BackupViewModel
import com.geyu.utils.ToActivity

@CreateViewModel(My_BackupViewModel::class)
class My_BackupActivity: BaseMvvmActivity<My_BackupContract.ViewModel, MyActivityBackupBinding>() ,My_BackupContract.View {
    override fun getLayoutId(): Int {
        return R.layout.my_activity_backup
    }

    override fun initView() {
        super.initView()
        mDataBinding.view = this
        initTopBarAndTopPadding(mDataBinding.topBar)

    }

    override fun dataExport() {
        mViewModel.dataExport()
    }

    override fun manager() {

        ToActivity.toActivity(this,My_BackupManagerActivity::class.java)
    }


}