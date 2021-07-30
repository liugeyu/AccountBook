package com.geyu.home.ui.activity;


import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.Record;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeActivityRecordEditBinding;
import com.geyu.home.ui.adapter.CategoryAdapter;
import com.geyu.home.ui.adapter.Home_RecordEidtFragmentAdapter;
import com.geyu.home.ui.contract.Home_RecordEditContract;
import com.geyu.home.ui.viewmodel.Home_RecordEditViewModel;
import com.geyu.utils.ToActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.viewpager2.widget.ViewPager2;

@CreateViewModel(Home_RecordEditViewModel.class)
public class Home_RecordEditActivity extends BaseMvvmActivity<Home_RecordEditViewModel, HomeActivityRecordEditBinding> implements Home_RecordEditContract.View {

    private Record oldRecord;

    PagerSnapHelper pagerSnapHelper;
    Home_RecordEidtFragmentAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_record_edit;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBarAndTopPadding(mDataBinding.topBar);

        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText("收入"));
        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText("支出"));
        mDataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mDataBinding.viewpage.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mDataBinding.viewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mDataBinding.tabLayout.setScrollPosition(position,positionOffset,true);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                if (position == 0) {
////                    mDataBinding.topBar.setTitle("收入");
//
//                } else {
//                    mDataBinding.topBar.setTitle("支出");
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        oldRecord = (Record) getIntent().getSerializableExtra(ToActivity.serializabkeKey);
        List<Integer> types = new ArrayList<>();
        types.add(CategoryModel.TYPE_EXPENSE);
        types.add(CategoryModel.TYPE_INCOME);
        adapter = new Home_RecordEidtFragmentAdapter(this,types,oldRecord);
        mDataBinding.viewpage.setAdapter(adapter);
    }


}
