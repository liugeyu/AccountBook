package com.geyu.my.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Window;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.geyu.Constant;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmFragment;
import com.geyu.callback.event.SwitchLanguage;
import com.geyu.my.R;
import com.geyu.my.databinding.MyFragmentMyBinding;
import com.geyu.my.ui.aty.My_BackupActivity;
import com.geyu.my.ui.contract.My_MyContract;
import com.geyu.my.ui.viewmodel.My_MyViewModel;
import com.geyu.utils.ToActivity;
import com.geyu.utils.language.MultiLanguages;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

@Route(path = Constant.MyClass.FRAGMENT_MY)
@CreateViewModel(My_MyViewModel.class)
public class My_MyFragment extends BaseMvvmFragment<My_MyViewModel, MyFragmentMyBinding> implements My_MyContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.my_fragment_my;
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.setView(this);
    }

    @Override
    public void backup() {
        ToActivity.toActivity(mActivity, My_BackupActivity.class);
    }

    @Override
    public void about() {

    }

    @Override
    public void switchLanguage() {
        final String[] cities = {"中文","英文"};
        final String[] locals = {"zh", "en"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("选择语言");
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean restart = MultiLanguages.setAppLanguage(mActivity,new Locale(locals[which]));

                if (restart) {
                    if (restart) {
//                        mActivity.finish();
                        EventBus.getDefault().post(new SwitchLanguage());
                    }
                }
            }
        });
        builder.show();
    }
}
