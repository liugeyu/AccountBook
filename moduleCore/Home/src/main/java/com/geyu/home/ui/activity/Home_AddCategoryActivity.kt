package com.geyu.home.ui.activity

import android.text.TextUtils
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.geyu.Constant
import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.callback.event.CategoryAdd
import com.geyu.database.ben.CategoryModel
import com.geyu.home.R
import com.geyu.home.databinding.HomeActivityAddCategoryBinding
import com.geyu.home.ui.adapter.HomeCommAdapter
import com.geyu.home.ui.contract.Home_AddCategoryContract
import com.geyu.home.ui.viewmodel.Home_AddCategoryViewModel
import com.geyu.manager.db.AccountBookManager
import com.geyu.manager.db.CategroyManager
import com.geyu.utils.StringUtils
import com.geyu.utils.ToActivity
import org.greenrobot.eventbus.EventBus

@Route(path = Constant.HomeClass.ACTIVITY_HOME_ADD_CATEGORY)
@CreateViewModel(Home_AddCategoryViewModel::class)
class Home_AddCategoryActivity: BaseMvvmActivity<Home_AddCategoryContract.ViewModel, HomeActivityAddCategoryBinding>(), Home_AddCategoryContract.View {
    @Autowired(name = "type")
    @JvmField var mType:Int = -1

    @Autowired(name = ToActivity.serializabkeKey)
    @JvmField var categoryModel:CategoryModel? = null


    var adapter:HomeCommAdapter<CategoryModel>? = null

    override fun getLayoutId(): Int {
        return R.layout.home_activity_add_category
    }

    override fun initView() {
        super.initView()
        ARouter.getInstance().inject(this)
        initTopBarAndTopPadding(mDataBinding.topBar)
        mDataBinding.view = this

        adapter = HomeCommAdapter(this,R.layout.home_item_add_category,null)
        mDataBinding.rv.layoutManager = GridLayoutManager(this,5,GridLayoutManager.VERTICAL,false)
        mDataBinding.rv.adapter = adapter
        adapter?.setListener(this)
    }


    override fun initData() {
        super.initData()

        mViewModel.getData().observe(this, {
            if (categoryModel != null) {
                mDataBinding.itemData = categoryModel
                it.forEach {
                    if (it.icon.equals(categoryModel?.icon)) {
                        it.isSelect = true
                    }
                }
            } else {
                it[0].isSelect = true
                categoryModel = it[0]
                mDataBinding.itemData = categoryModel
            }
            adapter?.datas = it
        })
        mViewModel.loadData(mType)
    }

    override fun add() {
        if (TextUtils.isEmpty(mDataBinding.etName.text.toString())) {
            showErrMessage(getString(R.string.please_input_name))
            return
        }
        categoryModel?.run {
            if (mType != -1) {
                type = mType
            }
//            name = mDataBinding.etName.text.toString()
            if (TextUtils.isEmpty(uniqueName) ) {
                uniqueName =  StringUtils.getUuid()
            }
            accountBookId = AccountBookManager.getAccountBookId()
            CategroyManager.saveOrUpdate(this)
            EventBus.getDefault().post(CategoryAdd())
        }

       onBack()
    }

    override fun onItemClickListener(t: CategoryModel?, position: Int) {
        adapter?.datas?.forEach {
            it.isSelect = it === t
            if (it.isSelect) {
                categoryModel?.icon = it.icon
                mDataBinding.itemData = categoryModel
            }
        }
        adapter?.notifyDataSetChanged()
    }
}