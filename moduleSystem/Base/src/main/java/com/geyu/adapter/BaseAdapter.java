package com.geyu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.geyu.base.BaseBen;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> datas = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public BaseAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }


    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = getViewHolder(parent, viewType);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindData(datas.get(position),position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
