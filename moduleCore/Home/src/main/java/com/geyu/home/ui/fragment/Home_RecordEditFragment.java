package com.geyu.home.ui.fragment;

import android.os.Bundle;

import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseFragment;
import com.geyu.base.BaseMvvmFragment;
import com.geyu.callback.NumericKeypadConfirm;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.Record;
import com.geyu.data.CategoryIconHelper;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeFragmentRecordEditBinding;
import com.geyu.home.ui.activity.Home_CategoryManagerActivity;
import com.geyu.home.ui.adapter.CategoryAdapter;
import com.geyu.home.ui.contract.Home_RecordEditFragmentContract;
import com.geyu.home.ui.viewmodel.Home_RecordEditFragmentViewModel;
import com.geyu.utils.AmountUtil;
import com.geyu.utils.ToActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

@CreateViewModel(Home_RecordEditFragmentViewModel.class)
public class Home_RecordEditFragment extends BaseMvvmFragment<Home_RecordEditFragmentContract.ViewModel, HomeFragmentRecordEditBinding> implements Home_RecordEditFragmentContract.View, NumericKeypadConfirm {
    private final static String key_record = "key_record";
    private final static String key_Record_type = "key_Record_type";
    private Record oldRecord;
    private int type = -1;

    private CategoryAdapter adapter;
    PagerSnapHelper pagerSnapHelper;

    public static BaseFragment getInstance(Record record,  int type){
        BaseFragment baseFragment = new Home_RecordEditFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(key_record,record);
        bundle.putInt(key_Record_type,type);
        baseFragment.setArguments(bundle);
        return baseFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_record_edit;
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.numberKeypad.setEditText(mDataBinding.tvAmount);
        mDataBinding.numberKeypad.setListener(this);

        mDataBinding.categoryRv.setLayoutManager(new GridLayoutManager(mActivity,2, LinearLayoutManager.HORIZONTAL,false));

        adapter = new CategoryAdapter(mActivity);
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
        if (CategoryIconHelper.IC_SETTING.equals(item.getIcon())) {
            ToActivity.toActivity(mActivity, Home_CategoryManagerActivity.class);
        } else {
            selectedItem(item.getUniqueName());
        }
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
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(key_Record_type);
            oldRecord = (Record) bundle.getSerializable(key_record);
        }
        mViewModel.initCategory(type);
//        oldRecord = (Record) getIntent().getSerializableExtra(ToActivity.serializabkeKey);
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
        CategoryModel categoryModel = adapter.getSelect();
        if (categoryModel == null) {
            showErrMessage("无记录类型,无法添加");
            return;
        }
        mViewModel.saveOrUpdateRecord(mDataBinding.tvAmount.getText().toString().replace("¥:","").trim(),categoryModel,oldRecord);
        onBack();
    }
}
