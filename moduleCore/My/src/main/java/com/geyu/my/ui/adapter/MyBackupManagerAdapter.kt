package com.geyu.my.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.geyu.adapter.BaseAdapter
import com.geyu.adapter.BaseViewHolder
import com.geyu.my.R
import com.geyu.my.ben.BackupFile
import com.geyu.my.databinding.MyItemBackupFileBinding
import com.geyu.my.ui.contract.My_BackupManagerContract

class MyBackupManagerAdapter(context: Context?) : BaseAdapter<BackupFile>(context) {
    override fun getViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*, *> {
        return ViewHolder(DataBindingUtil.inflate(layoutInflater,R.layout.my_item_backup_file,parent,false))
    }

    fun deleteItem(backupFile: BackupFile, position: Int){
        datas.remove(backupFile)
       notifyDataSetChanged()
    }

    class ViewHolder(vdb: MyItemBackupFileBinding) : BaseViewHolder<MyItemBackupFileBinding, BackupFile>(vdb) {
        override fun bindData(data: BackupFile?, position: Int, listener: Any?) {
            mBinding.itemData  = data
            mBinding.itemPosition = position
            mBinding.listener = listener as My_BackupManagerContract.View?
        }

    }
}