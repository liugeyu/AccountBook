package com.geyu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.geyu.base.R;
import com.geyu.callback.OnRefreshListener;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class BaseRefreshLayout extends FrameLayout implements OnRefreshLoadMoreListener {


    protected SmartRefreshLayout smartRefreshLayout;
    protected RecyclerView rv;
    protected OnRefreshListener listener;

    public BaseRefreshLayout(@NonNull Context context) {
        this(context,null);
    }

    public BaseRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initVew(context);
    }

    private void initVew(Context context) {
        LayoutInflater.from(context).inflate(R.layout.base_refresh_layout,this,true);
        rv = findViewById(R.id.base_rv);
        smartRefreshLayout = findViewById(R.id.refresh_layout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        rv.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        rv.setAdapter(adapter);
    }

    public void setListener(OnRefreshListener listener) {
        this.listener = listener;
    }
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        if (listener != null){
            listener.onLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (listener != null) {
            listener.onRefresh();
        }
    }

    public void refreshFinish(){
        smartRefreshLayout.finishRefresh();
    }

    public void loadMoreFinish(){
        smartRefreshLayout.finishLoadMore();
    }
}
