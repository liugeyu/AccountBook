package com.geyu.home.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.geyu.adapter.BaseAdapter;
import com.geyu.rx.RxSchedulersHelper;
import com.geyu.utils.ErrHandler;
import com.geyu.utils.ToastUtil;
import com.geyu.view.BaseRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RefreshRecyclerViewLayout extends BaseRefreshLayout {
    private final int starPage = 0;
    private int page = starPage;
    private RefreshRecyclerNetConfig netConfig;

    Disposable netDisposable;

    public static final int ACTION_NORMAL = -1;
    public static final int ACTION_FIRST_LOAD = 1;
    public static final int ACTION_REFRESH = 2;
    public static final int ACTION_LOAD_MORE = 3;

    protected int ACTION = ACTION_NORMAL;
    private boolean refreshCallback = true;

    public RefreshRecyclerViewLayout(@NonNull Context context) {
        super(context);
    }

    public RefreshRecyclerViewLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshRecyclerViewLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        ACTION = ACTION_LOAD_MORE;

        if (netConfig != null && rv.getAdapter() instanceof BaseAdapter) {
            requestData(page +1);
        } else {
            super.onLoadMore(refreshLayout);
        }
    }

    @Override
    public void loadMoreFinish() {
        ACTION = ACTION_NORMAL;

        super.loadMoreFinish();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        ACTION = ACTION_REFRESH;
        if (netConfig != null && rv.getAdapter() instanceof BaseAdapter) {
            // 自己刷新
            requestData(starPage);
        } else {
            super.onRefresh(refreshLayout);
        }
    }

    @Override
    public void refreshFinish() {
        ACTION = ACTION_NORMAL;
        super.refreshFinish();
    }

    public void firstLoad(){
        ACTION = ACTION_FIRST_LOAD;
        requestData(starPage);
    }
    public void setNetConfig( RefreshRecyclerNetConfig netConfig) {
        this.netConfig = netConfig;
    }
    /**
     * 获取接口数据
     * @param page
     */
    private void requestData(int page) {

        Map<String,Object> data = new HashMap<>();
        data.put("page",page);
        netConfig.getNetObservable(data,ACTION)
        .compose(RxSchedulersHelper.applyIoTransformer())
                .subscribe(new Observer<List>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        netDisposable = d;
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List ts) {

                        netSucceed(ts);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        ToastUtil.showToast(ErrHandler.getErrMsg(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    protected void netSucceed(List lists) {

        BaseAdapter adapter = (BaseAdapter) rv.getAdapter();
        switch (ACTION) {
            case ACTION_LOAD_MORE:
                page += 1;
                adapter.addDatas(lists);
                loadMoreFinish();
                break;
            case ACTION_REFRESH:
            case ACTION_FIRST_LOAD:
                page = starPage;
                adapter.setDatas(lists);
                refreshFinish();
                break;
        }
    }

    protected void netErr(){

    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (netDisposable != null && !netDisposable.isDisposed()) {
            netDisposable.dispose();
        }
    }

}
