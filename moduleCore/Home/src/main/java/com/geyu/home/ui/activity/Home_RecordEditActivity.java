package com.geyu.home.ui.activity;


import android.view.View;

import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.callback.NumericKeypadConfirm;
import com.geyu.callback.OnPageChanageListenner;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.Record;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeActivityRecordEditBinding;
import com.geyu.home.ui.adapter.CategoryAdapter;
import com.geyu.home.ui.contract.Home_RecordEditContract;
import com.geyu.home.ui.viewmodel.Home_RecordEditViewModel;
import com.geyu.utils.LLOG;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

@CreateViewModel(Home_RecordEditViewModel.class)
public class Home_RecordEditActivity extends BaseMvvmActivity<Home_RecordEditViewModel, HomeActivityRecordEditBinding> implements NumericKeypadConfirm, Home_RecordEditContract.View {


    private CategoryAdapter adapter;
    PagerSnapHelper pagerSnapHelper;
    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_record_edit;
    }


    @Override
    protected void initView() {
        super.initView();
        initTopBarAndTopPadding(mDataBinding.topBar);
        mDataBinding.numberKeypad.setEditText(mDataBinding.tvAmount);
        mDataBinding.numberKeypad.setListener(this);

        mDataBinding.categoryRv.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.HORIZONTAL,false));

        adapter = new CategoryAdapter(this);
        mDataBinding.categoryRv.setAdapter(adapter);

        adapter.setListener(this);
//        pagerSnapHelper = new PagerSnapHelper();
//
//        pagerSnapHelper.attachToRecyclerView(mDataBinding.categoryRv);
//
//        mDataBinding.categoryRv.addOnScrollListener(new OnPageChanageListenner(pagerSnapHelper) {
//            @Override
//            public void onPageSelected(int position) {
//                LLOG.e("翻页"+position);
//            }
//        });

    }

    public void categoryItemClick(CategoryModel item,int position) {
        for (CategoryModel data : adapter.getDatas()) {
            if (data == item) {
                data.isSelect = true;
            } else {
                data.isSelect = false;
            }
        }
        mDataBinding.setItemData(item);
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void initData() {
        super.initData();
       mViewModel.getCategoryData().observe(this, categoryModels -> {
           adapter.setDatas(categoryModels);
           if (categoryModels != null && categoryModels.size() > 0 ) {
               categoryItemClick(categoryModels.get(0),0);
           }
       });
    }

    @Override
    public void onConfirm() {
        mViewModel.saveOrUpdateRecord(mDataBinding.tvAmount.getText().toString(),adapter.getSelect());
        onBack();
    }


}
