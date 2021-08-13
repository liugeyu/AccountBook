package com.geyu.my.ui.viewmodel

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import com.geyu.database.ben.Account
import com.geyu.database.ben.AccountBook
import com.geyu.database.ben.CategoryModel
import com.geyu.database.ben.Record
import com.geyu.manager.db.AccountBookManager
import com.geyu.manager.db.AccountManager
import com.geyu.manager.db.CategroyManager
import com.geyu.manager.db.RecordDaoManager
import com.geyu.my.ben.DataExport
import com.geyu.my.ui.contract.My_BackupContract
import com.geyu.rx.LoadingObserver
import com.geyu.utils.FileUtil
import com.geyu.utils.TimeUtil
import io.reactivex.Observable
import java.io.File
import java.io.FileWriter

class My_BackupViewModel : My_BackupContract.ViewModel() {
    override fun dataExport() {
        Observable.zip(
                RecordDaoManager.findAll().onErrorReturn { return@onErrorReturn ArrayList<Record>() },
                AccountBookManager.findAll().onErrorReturn { return@onErrorReturn ArrayList<AccountBook>() },
                AccountManager.findAll().onErrorReturn { return@onErrorReturn ArrayList<Account>() },
                CategroyManager.findAll().onErrorReturn { return@onErrorReturn ArrayList<CategoryModel>() },
                { records: List<Record>?, accountBooks: List<AccountBook>?, accounts: List<Account>?, categoryModels: List<CategoryModel> ->
                    var dataExport = DataExport()
                    dataExport.accountBooks = accountBooks
                    dataExport.records = records
                    dataExport.accounts = accounts
                    dataExport.categorys = categoryModels
                    dataExport
                }
        ).map {
            var writ = FileWriter(File(FileUtil.getBackupFile(), TimeUtil.getTime("yyyy-MM-dd HH:mm:ss") + ".txt"))
            JSON.writeJSONStringTo(
                    it,
                    writ,
                    SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullNumberAsZero
            )
        }.subscribe( object : LoadingObserver<Unit>(this) {
            override fun onNext(t: Unit) {
                super.onNext(t)
                showMessage("导出成功")
            }
        })
    }

}