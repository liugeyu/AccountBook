package com.geyu.adapter;



import com.geyu.base.BaseBen;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public  abstract class BaseViewHolder<VDB extends ViewDataBinding, T> extends RecyclerView.ViewHolder {
    protected VDB mBinding ;
    public BaseViewHolder(@NonNull VDB vdb) {
        super(vdb.getRoot());
        this.mBinding = vdb;
    }

    /**
     * 绑定数据
     *
     * @param data 数据
     */
    protected abstract void bindData(T data, int position,Object listener);
}
