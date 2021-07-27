package com.geyu.home.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.geyu.Constant;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmFragment;
import com.geyu.callback.OnRefreshListener;
import com.geyu.database.ben.Record;
import com.geyu.db.RecordDaoManager;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeFragmentHomeBinding;
import com.geyu.home.ui.activity.Home_RecordEditActivity;
import com.geyu.home.ui.activity.Home_recordDetailActivity;
import com.geyu.home.ui.adapter.Home_HomeAdapter;
import com.geyu.home.ui.contract.Home_HomeContract;
import com.geyu.home.ui.viewmodel.Home_HomeViewModel;
import com.geyu.rx.RxSchedulersHelper;
import com.geyu.utils.ToActivity;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

@Route(path = Constant.HomeClass.FRAGMENT_HOME)
@CreateViewModel(Home_HomeViewModel.class)
public class Home_HomeFragment extends BaseMvvmFragment<Home_HomeViewModel, HomeFragmentHomeBinding> implements Home_HomeContract.View, OnRefreshListener {
    private Home_HomeAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_home;
    }


    @Override
    protected void initView() {
        super.initView();
        mDataBinding.setView(this);
        mDataBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Home_HomeAdapter(getContext());
        mDataBinding.rv.setAdapter(adapter);
        adapter.setListener(this);

        mDataBinding.rv.setListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mViewModel.getRecord().observe(this, records -> {
            adapter.setDatas(records);
            mDataBinding.rv.refreshFinish();
        });
        mViewModel.getLoadMore().observe(this,records -> {
            adapter.addDatas(records);
            mDataBinding.rv.loadMoreFinish();
        });
    }

    @Override
    public void addNewRecord() {
        ToActivity.toActivity(mActivity, Home_RecordEditActivity.class);
    }

    @Override
    public void onItemClick(Record itemData, int position) {
        ToActivity.toActivity(mActivity, Home_recordDetailActivity.class,itemData);
    }

    @Override
    public void onRefresh() {
        mViewModel.refresh();
    }

    @Override
    public void onLoadMore() {
        mViewModel.loadMore();
    }
}
