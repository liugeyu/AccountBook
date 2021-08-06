package com.geyu.rx;

import com.geyu.base.BaseViewModel;
import com.geyu.callback.DialogListener;
import com.geyu.utils.ErrHandler;
import com.geyu.utils.LLOG;
import com.geyu.utils.ScreenManager;
import com.geyu.view.dialog.BaseDialogFragment;
import com.geyu.view.dialog.LoadingDialog;

import androidx.fragment.app.FragmentActivity;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class LoadingObserver<T> implements Observer<T>, DialogListener {

    private final BaseViewModel baseViewModel;
    private Disposable disposable;
    private BaseDialogFragment loading;

    public LoadingObserver(BaseViewModel b) {
        this.baseViewModel = b;
    }

    @Override
    final public void onSubscribe(@NonNull Disposable d) {
        baseViewModel.setDisposable(d);
        disposable = d;
        if (ScreenManager.getInstance().getLastActivity() != null){
            FragmentActivity activity = (FragmentActivity) ScreenManager.getInstance().getLastActivity();
            loading = new LoadingDialog();
            loading.show(activity.getSupportFragmentManager(),"loading");
            loading.setDialogListener(this);
        }
    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        baseViewModel.showMessage(ErrHandler.getErrMsg(e));
    }

    @Override
    final public void onComplete() {
        baseViewModel.removeDisposable(disposable);
        if (loading != null && loading.getDialog() != null && loading.getDialog().isShowing()) {
            loading.dismiss();
        }
    }

    @Override
    public void dismiss() {
        LLOG.d("dismiss");
        baseViewModel.removeDisposable(disposable);
    }
}
