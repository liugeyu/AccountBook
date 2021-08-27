package com.geyu.home.ui.fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.geyu.Constant;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseDataFragment;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.callback.event.CategoryAdd;
import com.geyu.database.ben.CategoryModel;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeFragmentCategoryManagerBinding;
import com.geyu.home.ui.adapter.HomeCommAdapter;
import com.geyu.home.ui.contract.Home_categoryManagerFragmentContract;
import com.geyu.home.ui.viewmodel.Home_CategoryManagerFragmentViewModel;
import com.geyu.manager.db.CategroyManager;
import com.geyu.utils.LLOG;
import com.geyu.utils.ToActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.recyclerview.widget.LinearLayoutManager;

@CreateViewModel(Home_CategoryManagerFragmentViewModel.class)
public class Home_CategoryManagerFragment extends BaseDataFragment<Integer,Home_categoryManagerFragmentContract.ViewModel, HomeFragmentCategoryManagerBinding> implements Home_categoryManagerFragmentContract.View {

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
        mDataBinding.setView(this);
        adapter = new HomeCommAdapter<>(mActivity, R.layout.home_item_add_category, null);
        mDataBinding.rv.setLayoutManager(new LinearLayoutManager(mActivity));
        mDataBinding.rv.setAdapter(adapter);
        adapter.setListener(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void categoryAdd(CategoryAdd categoryAdd) {
        mViewModel.initData(type);
    }
    @Override
    protected boolean registerEventBus() {
        return true;
    }

    @Override
    public void add() {
        ARouter.getInstance().build(Constant.HomeClass.ACTIVITY_HOME_ADD_CATEGORY).withInt("type",type).navigation();
    }

    @Override
    public void onItemClickListener(CategoryModel categoryModel, int position) {
        ARouter.getInstance().build(Constant.HomeClass.ACTIVITY_HOME_ADD_CATEGORY).withInt("type",type).withSerializable(ToActivity.serializabkeKey,categoryModel).navigation();
    }
}
