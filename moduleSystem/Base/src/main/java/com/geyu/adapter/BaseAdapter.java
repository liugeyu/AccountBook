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
    protected LayoutInflater layoutInflater;

    protected Object o;
    public void setListener(Object o){
        this.o = o;
    }
    public BaseAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }
    public BaseAdapter(Context context,List<T> inDatas) {
        layoutInflater = LayoutInflater.from(context);
        if (inDatas != null && inDatas.size() > 0) {
            this.datas.addAll(inDatas);
        }
    }

    public void setDatas(List<T> datas){
        if (datas != null) {
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }


    public List<T> getDatas() {
        return datas;
    }

    public void addDatas(List<T> datas){
        if (datas != null) {
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
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
        holder.bindData(datas.get(position),position,o);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
