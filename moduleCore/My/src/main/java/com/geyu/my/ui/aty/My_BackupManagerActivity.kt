package com.geyu.my.ui.aty

import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.my.R
import com.geyu.my.databinding.MyActivityBackupManagerBinding
import com.geyu.my.ui.contract.My_BackupManagerContract
import com.geyu.my.ui.viewmodel.My_BackupManagerViewModel

@CreateViewModel(My_BackupManagerViewModel::class)
class My_BackupManagerActivity : BaseMvvmActivity<My_BackupManagerContract.ViewModel, MyActivityBackupManagerBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.my_activity_backup_manager
    }

}