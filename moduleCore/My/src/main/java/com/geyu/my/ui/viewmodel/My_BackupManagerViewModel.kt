package com.geyu.my.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geyu.my.ben.BackupFile
import com.geyu.my.ui.contract.My_BackupManagerContract
import com.geyu.utils.FileUtil
import com.geyu.utils.LLOG
import java.io.File

class My_BackupManagerViewModel : My_BackupManagerContract.ViewModel() {

    var page = 0
    val pageSize = 100000 // 先全展示吧,分页 删除文件后 源数据就变了, 再按现在的逻辑截取数据的话 肯定会有重复数据

    val _datas = MutableLiveData<List<BackupFile>>()
    val _moreData = MutableLiveData<List<BackupFile>>()
    override fun getDatas(): LiveData<List<BackupFile>> {
        return _datas
    }

    override fun loadMore() {
        var data = getFileData(page +1,pageSize)
        if (data?.isNotEmpty()) {
            page += 1
        }
        _moreData.value = data
    }

    override fun getLoadMore(): LiveData<List<BackupFile>> {
        return _moreData
    }

    override fun delete(backupFile: BackupFile) {
        // TODO: 2021/8/6 最好检查权限
        try {
            var file = File(backupFile?.path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
        }
    }


    override fun onCreate() {
        super.onCreate()
        initData()
    }

    private fun initData() {
        _datas.value =getFileData(page,pageSize)
    }

    /**
     * 获取从第index个开始的备份数据
     */
    private fun getFileData(page: Int, pageSize: Int): List<BackupFile> {
        val backupFile = FileUtil.getBackupFile()
        val files = backupFile.listFiles();
        if (files == null || files.isEmpty() || page * pageSize >= files.size) {
            return ArrayList<BackupFile>()
        }
        files.sort()
        val resule = ArrayList<BackupFile>()

        for (i in page * pageSize..(page + 1) * pageSize) {
            if (i >= files.size) {
                return resule;
            }
            val file = files.get(i);
            LLOG.e(file.name + " : " + file.absolutePath + " : " + file.path)
            val backupFile = BackupFile()
            backupFile.size = file.length()
            backupFile.name = file.name
            backupFile.path = file.path;
            backupFile.time = file.name
            resule.add(backupFile)
        }
        return resule
    }


}