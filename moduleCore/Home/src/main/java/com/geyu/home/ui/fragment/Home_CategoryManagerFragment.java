package com.geyu.home.ui.fragment;

import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseDataFragment;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.database.ben.CategoryModel;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeFragmentCategoryManagerBinding;
import com.geyu.home.ui.adapter.HomeCommAdapter;
import com.geyu.home.ui.contract.Home_categoryManagerFragmentContract;
import com.geyu.home.ui.viewmodel.Home_CategoryManagerFragmentViewModel;
import com.geyu.manager.db.CategroyManager;
import com.geyu.utils.LLOG;

import androidx.recyclerview.widget.LinearLayoutManager;

@CreateViewModel(Home_CategoryManagerFragmentViewModel.class)
public class Home_CategoryManagerFragment extends BaseDataFragment<Integer,Home_categoryManagerFragmentContract.ViewModel, HomeFragmentCategoryManagerBinding> {

    private int type = -1;
    private HomeCommAdapter<CategoryModel> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_category_manager;
    }

    @Override
    protected void getData(Integer integer) {
        super.getData(integer);
        LLOG.e("get data");
        type = integer;
    }


    @Override
    protected void initView() {
        super.initView();
        LLOG.e("init view");
        adapter = new HomeCommAdapter(mActivity,R.layout.home_item_category,null);
        mDataBinding.rv.setLayoutManager(new LinearLayoutManager(mActivity));
        mDataBinding.rv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        LLOG.e("init data");
        mViewModel.initData(type);
        mViewModel.getCategoryData().observe(this,categoryModels -> {
            adapter.setDatas(categoryModels);
        });
    }
}
