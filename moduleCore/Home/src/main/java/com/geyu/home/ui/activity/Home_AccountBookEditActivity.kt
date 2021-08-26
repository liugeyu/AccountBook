package com.geyu.home.ui.activity

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.geyu.Constant
import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.callback.event.RecordChanager
import com.geyu.database.ben.AccountBook
import com.geyu.home.R
import com.geyu.home.databinding.HomeActivityAccountBookEidtBinding
import com.geyu.home.ui.contract.Home_AccountBookEditContract
import com.geyu.home.ui.viewmodel.Home_AccountBookEditViewModel
import com.geyu.manager.SpManager
import com.geyu.manager.db.AccountBookManager
import com.geyu.manager.db.AccountManager
import com.geyu.utils.LLOG
import com.geyu.utils.ResUtils
import com.geyu.utils.ToActivity
import org.greenrobot.eventbus.EventBus

@Route(path = Constant.HomeClass.ACTIVITY_HOME_ACCOUNT_BOOK_EDIT)
@CreateViewModel(Home_AccountBookEditViewModel::class)
class Home_AccountBookEditActivity: BaseMvvmActivity<Home_AccountBookEditContract.ViewModel, HomeActivityAccountBookEidtBinding>(),Home_AccountBookEditContract.View {

    @Autowired(name = ToActivity.serializabkeKey)
    @JvmField var accountBook:AccountBook? = null
    override fun getLayoutId(): Int {
        return R.layout.home_activity_account_book_eidt
    }


    override fun initView() {
        ARouter.getInstance().inject(this)
        super.initView()
        initTopBarAndTopPadding(mDataBinding.topBar)
        LLOG.e("数据:$accountBook")
        mDataBinding.accountBook = accountBook
        mDataBinding.view = this
    }

    override fun save(accountBook: AccountBook) {
        AccountBookManager.saveOrUpdate(accountBook)
        if (AccountBookManager.getAccountBookId() == accountBook.accountBookId) {
            EventBus.getDefault().post(RecordChanager())
        }
        onBack()
    }

    override fun delete(accountBook: AccountBook) {
        LLOG.e(accountBook.accountBookName)
        val bookCount = AccountBookManager.getAccountBookCount()
        if (bookCount <= 1) {
            showErrMessage(ResUtils.getString(R.string.least_one_ledger))
            return
        }
        AccountBookManager.delete(accountBook)

        if (AccountBookManager.getAccountBookId() == accountBook.accountBookId) {
            var d = AccountBookManager.findAll().subscribe({
                SpManager.AccountBooks.saveBookId(it[0].accountBookId)
                EventBus.getDefault().post(RecordChanager())
                onBack()
            },{})
            addDisposable(d)
        } else {
            onBack()
        }




    }
}