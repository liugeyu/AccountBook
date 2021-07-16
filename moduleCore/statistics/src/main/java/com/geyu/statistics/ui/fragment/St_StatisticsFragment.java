package com.geyu.statistics.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.geyu.Constant;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmFragment;
import com.geyu.statistics.R;
import com.geyu.statistics.databinding.StFragmentStatistivsBinding;
import com.geyu.statistics.ui.viewmodel.St_StatisticsViewModel;
@Route(path = Constant.StatisticsClass.FRAGMENT_STATISTICS)
@CreateViewModel(St_StatisticsViewModel.class)
public class St_StatisticsFragment extends BaseMvvmFragment<St_StatisticsViewModel, StFragmentStatistivsBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.st_fragment_statistivs;
    }
}
