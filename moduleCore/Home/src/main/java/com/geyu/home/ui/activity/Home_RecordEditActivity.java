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
import com.geyu.utils.AmountUtil;
import com.geyu.utils.LLOG;
import com.geyu.utils.ToActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

@CreateViewModel(Home_RecordEditViewModel.class)
public class Home_RecordEditActivity extends BaseMvvmActivity<Home_RecordEditViewModel, HomeActivityRecordEditBinding> implements NumericKeypadConfirm, Home_RecordEditContract.View {

    private Record oldRecord;

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
//        for (CategoryModel data : adapter.getDatas()) {
//            if (data.getUniqueName().equals(item.getUniqueName())) {
//                data.isSelect = true;
//            } else {
//                data.isSelect = false;
//            }
//        }
//        mDataBinding.setItemData(item);
//        adapter.notifyDataSetChanged();
        selectedItem(item.getUniqueName());
    }

    public void selectedItem(String uniqueName) {
        for (CategoryModel data : adapter.getDatas()) {
            if (data.getUniqueName().equals(uniqueName)) {
                data.isSelect = true;
                mDataBinding.setItemData(data);
            } else {
                data.isSelect = false;
            }
        }

        adapter.notifyDataSetChanged();
    }
    @Override
    protected void initData() {
        super.initData();
        oldRecord = (Record) getIntent().getSerializableExtra(ToActivity.serializabkeKey);
       mViewModel.getCategoryData().observe(this, categoryModels -> {
           adapter.setDatas(categoryModels);
           if (categoryModels != null && categoryModels.size() > 0 ) {
               if (oldRecord != null) {
                   selectedItem(oldRecord.getCategoryUniqueName());
                   mDataBinding.setRecord(oldRecord);
               } else {
                   categoryItemClick(categoryModels.get(0),0);
               }
           }
       });
    }

    @Override
    public void onConfirm() {
        if (AmountUtil.amtToCent(mDataBinding.tvAmount.getText().toString().replace("¥:","").trim()) <= 0) {
            showErrMessage("请输入金额");
            return;
        }
        mViewModel.saveOrUpdateRecord(mDataBinding.tvAmount.getText().toString().replace("¥:","").trim(),adapter.getSelect(),oldRecord);
        onBack();
    }


}
