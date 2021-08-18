package com.geyu.home.ui.activity

import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.callback.event.RecordChanager
import com.geyu.database.ben.AccountBook
import com.geyu.database.ben.CategoryModel
import com.geyu.database.data.CategoryConstant
import com.geyu.database.data.CategoryIconHelper
import com.geyu.home.R
import com.geyu.home.databinding.HomeActivityCreateAccountBooksBinding
import com.geyu.home.ui.contract.Home_CreateAccountBookContract
import com.geyu.home.ui.viewmodel.Home_CreateAccountBookViewModel
import com.geyu.manager.db.AccountBookManager
import com.geyu.manager.db.CategroyManager
import com.geyu.utils.ToActivity
import org.greenrobot.eventbus.EventBus

@CreateViewModel(Home_CreateAccountBookViewModel::class)
class Home_CreateAccountBookActivity: BaseMvvmActivity<Home_CreateAccountBookViewModel, HomeActivityCreateAccountBooksBinding>(),Home_CreateAccountBookContract.View {
    var accountBook:AccountBook? = null
    override fun getLayoutId(): Int {
        return R.layout.home_activity_create_account_books
    }

    override fun initData() {
        super.initData()
        accountBook = intent.getSerializableExtra(ToActivity.serializabkeKey) as AccountBook?
        mDataBinding.accountBook = accountBook

        mDataBinding.view = this

    }

    override fun save(accountBook: AccountBook?) {
        accountBook?.run {
            accountBookId = System.currentTimeMillis()
            createTime = System.currentTimeMillis()
            initDefCategory(accountBookId)
        }
        AccountBookManager.save(accountBook)

        EventBus.getDefault().post(RecordChanager())
        showErrMessage("保存成功")
        onBack()
    }

    /**
     * 设置初始化分类数据
     */
    private fun initDefCategory(accountBookId:Long) {
        var otherExpens = CategoryModel()
        otherExpens.accountBookId = accountBookId
        otherExpens.icon = CategoryIconHelper.IC_NAME_OTHER
        otherExpens.uniqueName = CategoryConstant.NAME_OTHER_EXPENSE
        otherExpens.type = CategoryModel.TYPE_EXPENSE
        otherExpens.name = "其他"
        CategroyManager.save(otherExpens)

        var otherIncome = CategoryModel()
        otherIncome.accountBookId = accountBookId
        otherIncome.icon = CategoryIconHelper.IC_NAME_OTHER
        otherIncome.uniqueName = CategoryConstant.NAME_OTHER_IN_COME
        otherIncome.type = CategoryModel.TYPE_INCOME
        otherIncome.name = "其他"
        CategroyManager.save(otherIncome)

    }


}