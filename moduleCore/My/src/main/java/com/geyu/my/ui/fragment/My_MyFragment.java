package com.geyu.my.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.geyu.Constant;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmFragment;
import com.geyu.my.R;
import com.geyu.my.databinding.MyFragmentMyBinding;
import com.geyu.my.ui.contract.My_MyContract;
import com.geyu.my.ui.viewmodel.My_MyViewModel;
@Route(path = Constant.MyClass.FRAGMENT_MY)
@CreateViewModel(My_MyViewModel.class)
public class My_MyFragment extends BaseMvvmFragment<My_MyViewModel, MyFragmentMyBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.my_fragment_my;
    }
}
