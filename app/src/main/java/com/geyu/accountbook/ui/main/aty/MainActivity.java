package com.geyu.accountbook.ui.main.aty;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.geyu.Constant;
import com.geyu.accountbook.R;
import com.geyu.accountbook.databinding.ActivityMainBinding;
import com.geyu.accountbook.ui.main.viewmodel.MainViewModel;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseApplication;
import com.geyu.base.BaseFragment;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.database.TestDao;
import com.geyu.database.ben.Test;
import com.geyu.service.TestService;
import com.geyu.utils.SystemBarTintManagerHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umeng.analytics.MobclickAgent;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.disposables.Disposable;
import kotlin.jvm.functions.Function1;

@Route(path = Constant.MainClass.ACTIVITY_MAIN)
@CreateViewModel(MainViewModel.class)
public class MainActivity extends BaseMvvmActivity<MainViewModel, ActivityMainBinding> implements BottomNavigationView.OnNavigationItemSelectedListener {






    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected boolean isSupportSlideBack() {
        return false;
    }


    @Override
    protected void initView() {

        TestKt testKt = new TestKt();
        testKt.test();

        testKt.test3(new Function1<TestKt, Integer>() {
            @Override
            public Integer invoke(TestKt testKt) {
                return 1;
            }
        });
        Test testData = new Test();
        testData.setMsg(""+System.currentTimeMillis());
        testData.setMsg2("msg2");
        BaseApplication.getmDaoSession().getTestDao().insert(testData);

        List<Test> result = BaseApplication.getmDaoSession().getTestDao().queryBuilder().where(TestDao.Properties.Msg.eq("")).list();
//
//        BaseApplication.getmDaoSession().getTestDao().delete(testData);
//
//        BaseApplication.getmDaoSession().getTestDao().queryBuilder().where(null).buildDelete();
//
//        BaseApplication.getmDaoSession().getTestDao().update();

        SystemBarTintManagerHelper.getInsatance().titleBarPaddingTop(mDataBinding.container);

        mDataBinding.navView.setOnNavigationItemSelectedListener(this);
        showFragment(R.id.nav_host_fragment,(BaseFragment) ARouter.getInstance().build(Constant.HomeClass.FRAGMENT_HOME).navigation());

        Map<String,Object> test = new HashMap<>();
        test.put("testkey","testData");
        MobclickAgent.onEventObject(this,"string_test_123",test);

        startService(new Intent(this, TestService.class));

    }



    @Override
    protected void initData() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(R.id.nav_host_fragment, (BaseFragment) ARouter.getInstance().build(Constant.HomeClass.FRAGMENT_HOME).navigation());
                break;
            case R.id.navigation_statistics:
                showFragment(R.id.nav_host_fragment, (BaseFragment) ARouter.getInstance().build(Constant.StatisticsClass.FRAGMENT_STATISTICS).navigation());
                break;
            case R.id.navigation_my:
                showFragment(R.id.nav_host_fragment,(BaseFragment) ARouter.getInstance().build(Constant.MyClass.FRAGMENT_MY).navigation());
                break;
        }
        return true;
    }
    FragmentManager fragmentManager = getSupportFragmentManager();
    private BaseFragment showFragment;
    private void showFragment(int contentId, BaseFragment inFragment) {
        if (inFragment == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (showFragment != null) {
            fragmentTransaction.hide(showFragment);
        }
        BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(inFragment.getClass().getName());
        if (fragment != null) {
            fragmentTransaction.show(fragment);
            showFragment = fragment;
        } else {
            showFragment = inFragment;
            fragmentTransaction.add(contentId,showFragment,inFragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }


    private long lastKeyBack = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - lastKeyBack > 2000) {
                lastKeyBack = System.currentTimeMillis();
                showErrMessage("两秒内再按返回");
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}