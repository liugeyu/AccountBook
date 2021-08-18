package com.geyu.home.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.model.GuidePage;
import com.geyu.Constant;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmFragment;
import com.geyu.callback.OnRefreshListener;
import com.geyu.callback.event.RecordChanager;
import com.geyu.database.ben.Record;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeFragmentHomeBinding;
import com.geyu.home.ui.activity.Home_RecordEditActivity;
import com.geyu.home.ui.activity.Home_SearchActivity;
import com.geyu.home.ui.activity.Home_recordDetailActivity;
import com.geyu.home.ui.adapter.Home_HomeAdapter;
import com.geyu.home.ui.contract.Home_HomeContract;
import com.geyu.home.ui.dialog.SelectAccountBookDialogFragment;
import com.geyu.home.ui.view.RefreshRecyclerNetConfig;
import com.geyu.home.ui.viewmodel.Home_HomeViewModel;
import com.geyu.manager.db.AccountBookManager;
import com.geyu.utils.ToActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Observable;

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

        NewbieGuide.with(this)
                .setLabel("guild1")
                .addGuidePage(GuidePage.newInstance()
                .addHighLight(mDataBinding.fab).setLayoutRes(R.layout.home_home_fab_guide))
                .addGuidePage(GuidePage.newInstance().addHighLight(mDataBinding.searchIv).setLayoutRes(R.layout.home_home_fab_guide)).show();
    }

    @Override
    protected void initData() {
        super.initData();
//        mViewModel.getRecord().observe(this, records -> {
//            adapter.setDatas(records);
//            mDataBinding.rv.refreshFinish();
//        });

        mDataBinding.setAccountBook(AccountBookManager.findAccountBook());
        mDataBinding.rv.setNetConfig(new RefreshRecyclerNetConfig<Record>() {
            @Override
            public Observable<List<Record>> getNetObservable(Map<String,Object> map, int action) {
                return mViewModel.getRecords(map);
            }
        });
        mDataBinding.rv.firstLoad();
//        mViewModel.getLoadMore().observe(this,records -> {
//            adapter.addDatas(records);
//            mDataBinding.rv.loadMoreFinish();
//        });
    }

    @Override
    protected boolean registerEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecrodChanager(RecordChanager event){
        mDataBinding.setAccountBook(AccountBookManager.findAccountBook());
        mDataBinding.rv.firstLoad();
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
    public void toSearch() {

        Bundle options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(
                    mActivity, mDataBinding.searchIv,
                    "transition_search_back"
            ).toBundle();
        }
        if (options != null) {
            startActivity(new Intent(mActivity,Home_SearchActivity.class),options);
        }else {
            ToActivity.toActivity(mActivity, Home_SearchActivity.class);
        }
    }

    @Override
    public void selectAccountBook() {
        SelectAccountBookDialogFragment dialogFragment = new SelectAccountBookDialogFragment();
        dialogFragment.show(mActivity.getSupportFragmentManager());
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
