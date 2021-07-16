package com.geyu.accountbook.ui.main.aty;

import com.alibaba.android.arouter.launcher.ARouter;
import com.geyu.Constant;
import com.geyu.accountbook.R;
import com.geyu.accountbook.databinding.ActivitySplashBinding;
import com.geyu.accountbook.ui.main.viewmodel.SplashViewModel;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.utils.TimeUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

@CreateViewModel(SplashViewModel.class)
public class SplashActivity extends BaseMvvmActivity<SplashViewModel, ActivitySplashBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean isNeedStarBar() {
        return false;
    }

    @Override
    protected void initView() {
        super.initView();

        Disposable disposable = TimeUtil.countDown(3)
                .subscribe(l -> {
                    ARouter.getInstance().build(Constant.MainClass.ACTIVITY_MAIN).navigation();
                    finish();
                }, throwable -> {});
        addDisposable(disposable);
    }
}
