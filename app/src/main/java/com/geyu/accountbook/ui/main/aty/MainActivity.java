 package com.geyu.accountbook.ui.main.aty;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.geyu.Constant;
import com.geyu.accountbook.R;
import com.geyu.accountbook.databinding.ActivityMainBinding;
import com.geyu.accountbook.ui.main.viewmodel.MainViewModel;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseFragment;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.callback.event.SwitchLanguage;
import com.geyu.service.TestService;
import com.geyu.utils.LLOG;
import com.geyu.utils.SystemBarTintManagerHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umeng.analytics.MobclickAgent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

@Route(path = Constant.MainClass.ACTIVITY_MAIN)
@CreateViewModel(MainViewModel.class)
public class MainActivity extends BaseMvvmActivity<MainViewModel, ActivityMainBinding> implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Autowired
    String test;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

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
        ARouter.getInstance().inject(this);
        LLOG.e(test+"   -----------------");

        EventBus.getDefault().register(this);

        List<String> file = new ArrayList<>();
        String[] fineNames = new String[file.size()];
        file.toArray(fineNames);

        LLOG.e("空数组"+ JSON.toJSONString(fineNames));
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
        LLOG.e(test+"sb");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchLanguage(SwitchLanguage switchLanguage) {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}