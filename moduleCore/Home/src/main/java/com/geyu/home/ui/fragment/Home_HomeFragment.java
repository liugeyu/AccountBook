package com.geyu.home.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.geyu.Constant;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmFragment;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeFragmentHomeBinding;
import com.geyu.home.ui.viewmodel.Home_HomeViewModel;

@Route(path = Constant.HomeClass.FRAGMENT_HOME)
@CreateViewModel(Home_HomeViewModel.class)
public class Home_HomeFragment extends BaseMvvmFragment<Home_HomeViewModel, HomeFragmentHomeBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_home;
    }

}
