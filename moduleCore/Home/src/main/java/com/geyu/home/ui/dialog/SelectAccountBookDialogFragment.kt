package com.geyu.home.ui.dialog

import android.content.Intent
import android.view.Gravity
import androidx.recyclerview.widget.GridLayoutManager
import com.geyu.base.BaseMvvmView
import com.geyu.callback.event.RecordChanager
import com.geyu.database.ben.AccountBook
import com.geyu.home.R
import com.geyu.home.databinding.HomeDialogSelectAccountBookBinding
import com.geyu.home.ui.activity.Home_AddAccountBookActivity
import com.geyu.home.ui.adapter.HomeCommAdapter
import com.geyu.manager.SpManager
import com.geyu.manager.db.AccountBookManager
import com.geyu.utils.ToActivity
import com.geyu.view.dialog.BaseDialogFragment
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

class SelectAccountBookDialogFragment :BaseDialogFragment<HomeDialogSelectAccountBookBinding>() ,BaseMvvmView<AccountBook>{

    override fun setDisplayAnimation(): Int {
        return TYPE_LEFT
    }

    override fun getGravity(): Int {
        return Gravity.CENTER
    }

    override fun getLayoutId(): Int {
        return R.layout.home_dialog_select_account_book
    }

    override fun initView() {
        super.initView()
        mDataBind.view = this
    }
    override fun initData() {
        super.initData()
        var d = AccountBookManager.findAll().subscribe({
            mDataBind.rv.layoutManager = GridLayoutManager(context,3)
            var adapter = HomeCommAdapter<AccountBook>(context,R.layout.home_item_account_book,it)
            mDataBind.rv.adapter = adapter
            adapter.setListener(this)
        },{
        })
        addDisposable(d)
    }

    fun addBook(){
        startActivity(Intent(context,Home_AddAccountBookActivity::class.java))
        dismiss()
    }

    override fun onItemClickListener(t: AccountBook?, position: Int) {

        t?.let {
            SpManager.AccountBooks.saveBookId(it.accountBookId)
        }
        EventBus.getDefault().post(RecordChanager())
        dismiss()
    }
}