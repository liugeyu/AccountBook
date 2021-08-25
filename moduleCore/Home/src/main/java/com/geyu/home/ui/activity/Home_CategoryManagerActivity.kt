package com.geyu.home.ui.activity

import androidx.viewpager2.widget.ViewPager2
import com.geyu.adapter.BaseFragmentPageAdapter
import com.geyu.base.Annotation.CreateViewModel
import com.geyu.base.BaseMvvmActivity
import com.geyu.ben.FragmentBen
import com.geyu.database.ben.CategoryModel
import com.geyu.home.R
import com.geyu.home.databinding.HomeActivityCategoryManagerBinding
import com.geyu.home.ui.contract.Home_CategoryManagerContract
import com.geyu.home.ui.fragment.Home_CategoryManagerFragment
import com.geyu.home.ui.viewmodel.Home_CategoryManagerViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.io.Serializable

@CreateViewModel(Home_CategoryManagerViewModel::class)
class Home_CategoryManagerActivity: BaseMvvmActivity<Home_CategoryManagerContract.ViewModel, HomeActivityCategoryManagerBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.home_activity_category_manager
    }

    override fun initView() {
        super.initView()
        initTopBarAndTopPadding(mDataBinding.topBar)
        var fragments = ArrayList<FragmentBen<Int>>()

        fragments.add(FragmentBen(Home_CategoryManagerFragment::class.java, CategoryModel.TYPE_EXPENSE))
        fragments.add(FragmentBen(Home_CategoryManagerFragment::class.java, CategoryModel.TYPE_INCOME))
        mDataBinding.viewpage2.adapter = BaseFragmentPageAdapter(fragments as List<FragmentBen<Serializable>>?, this)


        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText("支出"))
        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText("收入"))
        mDataBinding.tabLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.run {
                    mDataBinding.viewpage2.currentItem = position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        mDataBinding.viewpage2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                mDataBinding.tabLayout.setScrollPosition(position,positionOffset,true)
            }
        })
    }
}

