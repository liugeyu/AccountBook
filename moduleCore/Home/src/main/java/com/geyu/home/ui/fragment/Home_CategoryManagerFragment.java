package com.geyu.home.ui.fragment;

import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseDataFragment;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeFragmentCategoryManagerBinding;
import com.geyu.home.ui.contract.Home_categoryManagerFragmentContract;
import com.geyu.home.ui.viewmodel.Home_CategoryManagerFragmentViewModel;

@CreateViewModel(Home_CategoryManagerFragmentViewModel.class)
public class Home_CategoryManagerFragment extends BaseDataFragment<Integer,Home_categoryManagerFragmentContract.ViewModel, HomeFragmentCategoryManagerBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_category_manager;
    }

    @Override
    protected void getData(Integer integer) {
        super.getData(integer);

    }
}
