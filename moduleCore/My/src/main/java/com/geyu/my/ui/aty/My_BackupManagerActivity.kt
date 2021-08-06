package com.geyu.my.ui.aty

import androidx.recyclerview.widget.LinearLayoutManager
import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.callback.OnRefreshListener
import com.geyu.my.R
import com.geyu.my.ben.BackupFile
import com.geyu.my.databinding.MyActivityBackupManagerBinding
import com.geyu.my.ui.adapter.MyBackupManagerAdapter
import com.geyu.my.ui.contract.My_BackupManagerContract
import com.geyu.my.ui.viewmodel.My_BackupManagerViewModel
import com.geyu.utils.ToActivity

@CreateViewModel(My_BackupManagerViewModel::class)
class My_BackupManagerActivity : BaseMvvmActivity<My_BackupManagerContract.ViewModel, MyActivityBackupManagerBinding>(),My_BackupManagerContract.View, OnRefreshListener {

    var adapter:MyBackupManagerAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.my_activity_backup_manager
    }

    override fun initView() {
        super.initView()
        initTopBarAndTopPadding(mDataBinding.topBar)
        mDataBinding.rv.enableRefresh(false)
        adapter = MyBackupManagerAdapter(this);

        mDataBinding.rv.setLayoutManager(LinearLayoutManager(this))
        mDataBinding.rv.setAdapter(adapter)
        adapter?.setListener(this)
        mDataBinding.rv.setListener(this)

    }

    override fun initData() {
        super.initData()
        mViewModel.getDatas().observe(this){
            adapter?.datas = it
        }
        mViewModel.getLoadMore().observe(this){
            adapter?.addDatas(it)
            mDataBinding.rv.loadMoreFinish()
        }
    }

    override fun openDetail(backupFile: BackupFile) {
        ToActivity.toActivity(this,My_BackupDetailActivity::class.java,backupFile)
    }

    override fun deleteItem(backupFile: BackupFile,itemPoistion:Int) {
        adapter?.deleteItem(backupFile,itemPoistion)
        mViewModel.delete(backupFile);

    }

    override fun onRefresh() {
    }

    override fun onLoadMore() {
        mViewModel.loadMore()
    }


}