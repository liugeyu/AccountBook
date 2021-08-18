package com.geyu.home.ui.activity

import androidx.recyclerview.widget.GridLayoutManager
import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.database.ben.AccountBook
import com.geyu.home.R
import com.geyu.home.databinding.HomeActivityAddAccountBookBinding
import com.geyu.home.ui.adapter.HomeCommAdapter
import com.geyu.home.ui.contract.Home_AddAccountBookdContract
import com.geyu.home.ui.viewmodel.Home_AddAccountBookViewModel
import com.geyu.utils.ToActivity
import java.util.ArrayList

@CreateViewModel(Home_AddAccountBookViewModel::class)
class Home_AddAccountBookActivity : BaseMvvmActivity<Home_AddAccountBookdContract.ViewModel, HomeActivityAddAccountBookBinding>(),Home_AddAccountBookdContract.View {
    var adapter:HomeCommAdapter<AccountBook>? = null
    override fun getLayoutId(): Int {
        return R.layout.home_activity_add_account_book
    }


    override fun initView() {
        super.initView()

        initTopBarAndTopPadding(mDataBinding.topBar)
    }

    override fun initData() {
        super.initData()

        var accountBook = initAccountBooks()
        adapter = HomeCommAdapter<AccountBook>(this,R.layout.home_item_account_book,accountBook)
        mDataBinding.rv.adapter = adapter
        mDataBinding.rv.layoutManager = GridLayoutManager(this,3)
        adapter?.setListener(this)
    }

    private fun initAccountBooks():List<AccountBook> {
        var accountBooks = ArrayList<AccountBook>()


        var everyday = AccountBook()
        everyday.accountBookName = resources.getString(R.string.everyday)
        accountBooks.add(everyday)

        var business = AccountBook()
        business.accountBookName = resources.getString(R.string.business)
        accountBooks.add(business)

        var humanFeelings = AccountBook()
        humanFeelings.accountBookName = resources.getString(R.string.human_feeling)
        accountBooks.add(humanFeelings)

        var investment = AccountBook()
        investment.accountBookName = resources.getString(R.string.investment)
        accountBooks.add(investment)

        var fitment = AccountBook()
        fitment.accountBookName = resources.getString(R.string.fitment)
        accountBooks.add(fitment)

        var campus = AccountBook()
        campus.accountBookName = resources.getString(R.string.campus)
        accountBooks.add(campus)

        var travel = AccountBook()
        travel.accountBookName = resources.getString(R.string.travel)
        accountBooks.add(travel)
        return accountBooks
    }

    override fun onItemClickListener(t: AccountBook?, position: Int) {
        ToActivity.toActivity(this,Home_CreateAccountBookActivity::class.java,t)
        onBack()
    }

}