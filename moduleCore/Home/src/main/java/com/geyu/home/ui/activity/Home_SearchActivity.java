package com.geyu.home.ui.activity;

import android.content.Context;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geyu.adapter.BaseViewHolder;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmActivity;
import com.geyu.database.ben.Record;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeActivitySearchBinding;
import com.geyu.home.ui.adapter.HomeBaseAdapter;
import com.geyu.home.ui.contract.Home_SearchContract;
import com.geyu.home.ui.viewmodel.Home_SearchViewModel;
import com.geyu.utils.LLOG;
import com.geyu.utils.SystemBarTintManagerHelper;
import com.geyu.utils.ToActivity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
@CreateViewModel(Home_SearchViewModel.class)
public class Home_SearchActivity extends BaseMvvmActivity<Home_SearchContract.ViewModel, HomeActivitySearchBinding> implements TextView.OnEditorActionListener, Home_SearchContract.View {
    private SearchAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_search;
    }


    @Override
    protected void initView() {
        super.initView();
        SystemBarTintManagerHelper.getInsatance().titleBarPaddingTop(mDataBinding.topbar);
        mDataBinding.setView(this);
        mDataBinding.etInput.setOnEditorActionListener(this);

        mViewModel.starSearch(mDataBinding.etInput);

        mDataBinding.rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter(this);
        adapter.setListener(this);
        mDataBinding.rv.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        super.initData();
        mViewModel.getSearcher().observe(this,records -> {
            LLOG.d(records.toString());
            adapter.setDatas(records);
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        if (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
//            if (!TextUtils.isEmpty(mDataBinding.etInput.getText().toString())){
//                mViewModel.starSearch(mDataBinding.etInput.getText().toString());
//            }
//            return true;
//        }
        return false;
    }

    @Override
    public void onItemClickListener(Record record, int position) {
        ToActivity.toActivity(this, Home_recordDetailActivity.class,record);
    }

    @Override
    public void onBackActivity() {
        onBack();
    }

    static class SearchAdapter extends HomeBaseAdapter<Record> {

        public SearchAdapter(Context context) {
            super(context);
        }

        @Override
        protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
            return new HomeBaseViewHolder(DataBindingUtil.inflate(layoutInflater,R.layout.home_item_search,parent,false));
        }
    }
}
