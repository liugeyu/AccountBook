package com.geyu.home.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.geyu.adapter.BaseViewHolder;

import java.util.List;

import androidx.databinding.DataBindingUtil;

public class HomeCommAdapter<T>  extends HomeBaseAdapter<T>{
    private int itemLayoutId;
    public HomeCommAdapter(Context context, int layoutId, List<T> datas) {
        super(context,datas);
        itemLayoutId = layoutId;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new HomeBaseViewHolder(DataBindingUtil.inflate(layoutInflater,itemLayoutId,parent,false));
    }
}
