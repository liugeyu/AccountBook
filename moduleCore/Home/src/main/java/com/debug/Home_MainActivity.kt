package com.debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.geyu.base.BaseActivity
import com.geyu.home.R
import com.geyu.home.ui.fragment.Home_HomeFragment
import com.geyu.utils.SystemBarTintManagerHelper

class Home_MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.home_activity_home__main
    }

    override fun initView() {
        super.initView()

        SystemBarTintManagerHelper.getInsatance().titleBarPaddingTop(findViewById(R.id.fl_content))
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_content,Home_HomeFragment(),"home").commit()
    }
}