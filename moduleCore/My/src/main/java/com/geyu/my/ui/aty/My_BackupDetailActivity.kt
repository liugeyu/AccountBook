package com.geyu.my.ui.aty

import com.alibaba.fastjson.JSON
import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.my.R
import com.geyu.my.ben.BackupFile
import com.geyu.my.ben.DataExport
import com.geyu.my.databinding.MyActivityBackupDetailBinding
import com.geyu.my.ui.contract.My_BackupDetailContract
import com.geyu.my.ui.viewmodel.My_BackupDetailViewModel
import com.geyu.utils.LLOG
import com.geyu.utils.ToActivity
import okio.Okio
import java.io.File
import java.nio.charset.Charset

@CreateViewModel(My_BackupDetailViewModel::class)
class My_BackupDetailActivity : BaseMvvmActivity<My_BackupDetailContract.ViewModel, MyActivityBackupDetailBinding>() {
    var backupFile:BackupFile? = null
    override fun getLayoutId(): Int {
        return R.layout.my_activity_backup_detail
    }

    override fun initView() {
        super.initView()
        initTopBarAndTopPadding(mDataBinding.topBar)

    }

    override fun initData() {
        super.initData()
        backupFile = intent.getSerializableExtra(ToActivity.serializabkeKey) as BackupFile?
       backupFile?.run {
           var file = File(path)
           var sourec = Okio.source(file)
           var buffer = Okio.buffer(sourec)
           var str = buffer.readString(Charset.forName("UTF-8"))
           var dataexport = JSON.parseObject(str,DataExport::class.java)
           LLOG.d("str $str")
       }


    }
}