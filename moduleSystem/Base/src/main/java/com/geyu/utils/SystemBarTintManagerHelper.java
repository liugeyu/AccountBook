package com.geyu.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.geyu.base.BaseApplication;
import com.geyu.base.R;
import com.gyf.immersionbar.ImmersionBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * @Author zhangshuqi
 * @CreateTime 2018/3/28
 * @Describe
 */

public class SystemBarTintManagerHelper {


    private static class SystemBarTintManagerHelperSingleton {
        private static final SystemBarTintManagerHelper instance = new SystemBarTintManagerHelper();
    }

    private SystemBarTintManagerHelper() {
    }

    public static SystemBarTintManagerHelper getInsatance() {
        return SystemBarTintManagerHelperSingleton.instance;
    }

    public void initStateBar(AppCompatActivity appCompatActivity, int color, boolean transparentStateBar) {
        if (!transparentStateBar) {
            ImmersionBar.with(appCompatActivity).statusBarColor(color)
                    .navigationBarColor(color)
                    .statusBarDarkFont(true, 0.2f) .init();
            //   StatusBarCompat.setStatusBarColor(appCompatActivity, UIUtils.getColor(color));
        } else {
            ImmersionBar.with(appCompatActivity)
                    .navigationBarColor(color)
                    .statusBarColor(R.color.transparent).statusBarDarkFont(true, 0.2f).init();
            // setTransparentStateBar(appCompatActivity);
        }
    }
    public void initStateBar(Fragment appCompatActivity, int color, boolean transparentStateBar, boolean isFitsSystemWindows) {
        if (!transparentStateBar) {
            ImmersionBar.with(appCompatActivity).statusBarColor(color)
                    .navigationBarColor(color)
                    .statusBarDarkFont(true, 0.2f).fitsSystemWindows(isFitsSystemWindows).init();
            //   StatusBarCompat.setStatusBarColor(appCompatActivity, UIUtils.getColor(color));
        } else {
            ImmersionBar.with(appCompatActivity).statusBarColor(R.color.transparent)
                    .navigationBarColor(color)
                    .statusBarDarkFont(true, 0.2f).fitsSystemWindows(isFitsSystemWindows).init();
            // setTransparentStateBar(appCompatActivity);
        }
    }

    public void setStatusBarTintResource(AppCompatActivity appCompatActivity, int color) {
        ImmersionBar.with(appCompatActivity).statusBarColor(color).statusBarDarkFont(true, 0.2f).init();
    }

    public void setTintAlpha(AppCompatActivity appCompatActivity, float color) {
        if (color >= 0) color = 0;
        ImmersionBar.with(appCompatActivity).statusBarAlpha(color).init();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on, AppCompatActivity appCompatActivity) {
        Window win = appCompatActivity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



    public void titleBarMarginTop(AppCompatActivity appCompatActivity, View view) {
        ImmersionBar.with(appCompatActivity).titleBarMarginTop(view);
    }

    public void fitsSystemWindows(AppCompatActivity appCompatActivity, boolean f) {
        ImmersionBar.with(appCompatActivity).fitsSystemWindows(f).init();
    }

    public void titleBarPaddingTop(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = DisplayUtils.getStatusBarHeight(BaseApplication.getContext());
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }


}
