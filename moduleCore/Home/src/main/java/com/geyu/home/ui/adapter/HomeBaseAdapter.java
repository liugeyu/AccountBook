package com.geyu.home.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.geyu.adapter.BaseAdapter;
import com.geyu.adapter.BaseViewHolder;
import com.geyu.home.BR;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

public abstract class HomeBaseAdapter<T> extends BaseAdapter<T> {
    public HomeBaseAdapter(Context context) {
        super(context);
    }


    static class HomeBaseViewHolder extends BaseViewHolder{

        public HomeBaseViewHolder(@NonNull ViewDataBinding dataBinding) {
            super(dataBinding);
        }

        @Override
        protected void bindData(Object data, int position,Object listener) {
            mBinding.setVariable(BR.itemData,data);
            mBinding.setVariable(BR.itemPosition,position);
            mBinding.setVariable(BR.listener,listener);
            mBinding.executePendingBindings();
        }
    }

}
