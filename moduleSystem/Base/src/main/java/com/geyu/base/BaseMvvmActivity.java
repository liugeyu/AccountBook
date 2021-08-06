package com.geyu.base;

import com.geyu.Constant;
import com.geyu.callback.event.ShowLoading;
import com.geyu.utils.LLOG;
import com.geyu.utils.ToastUtil;
import com.geyu.view.dialog.BaseDialogFragment;
import com.geyu.view.dialog.LoadingDialog;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseMvvmActivity<VM extends BaseViewModel,VDB extends ViewDataBinding> extends BaseActivity{

    protected VDB mDataBinding;
    protected VM mViewModel;

    @Override
    protected void initDataBinding(int laytId) {

        mDataBinding = DataBindingUtil.setContentView(this,laytId);
        mViewModel = ViewModelFactoryImpl.getInstance().createViewModel(this);
        getLifecycle().addObserver(mViewModel);

        mDataBinding.setLifecycleOwner(this);

        initErrMsg();
        initLoading();
    }

    protected  void initLoading() {
        mViewModel.getLoading().observe(this, this::showLoading);
    }

    private BaseDialogFragment loadingDialog;
    protected void showLoading(ShowLoading s) {
        if (Constant.ShowLoading.TYPE_DISMISS == s.getType()){
            // 隐藏dialog
            LLOG.d("dialog dismiss");
            if (loadingDialog != null && loadingDialog.getDialog()!= null && loadingDialog.getDialog().isShowing()) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }

        } else if (Constant.ShowLoading.TYPE_DEF == s.getType()) {
            // 显示默认dialog
            LLOG.d("dialog show");
            if (loadingDialog != null && loadingDialog.getDialog()!= null && loadingDialog.getDialog().isShowing()) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
            loadingDialog = new LoadingDialog();
            loadingDialog.show(getSupportFragmentManager(),"loading");

        }
    }

    protected  void initErrMsg() {
        mViewModel.getMessage().observe(this, this::showErrMessage);
    }

    protected void showErrMessage(String errMsg){
        ToastUtil.showToast(errMsg);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected boolean isDataBinding() {
        return true;
    }
}
