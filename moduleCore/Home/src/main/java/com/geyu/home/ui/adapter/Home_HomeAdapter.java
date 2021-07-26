package com.geyu.home.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.geyu.adapter.BaseAdapter;
import com.geyu.adapter.BaseViewHolder;
import com.geyu.database.ben.Record;
import com.geyu.home.BR;
import com.geyu.home.R;
import com.geyu.home.databinding.HomeExpendItemBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class Home_HomeAdapter extends BaseAdapter<Record> {
    public Home_HomeAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new ExpendViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.home_expend_item,parent,false));
    }


    static class ExpendViewHolder extends BaseViewHolder<HomeExpendItemBinding,Record>{

        public ExpendViewHolder(@NonNull HomeExpendItemBinding homeExpendItemBinding) {
            super(homeExpendItemBinding);
        }

        @Override
        protected void bindData(Record data, int position,Object listener) {
            mBinding.setVariable(BR.itemData,data);
            mBinding.setVariable(BR.itemPosition,position);
            mBinding.setVariable(BR.listener,listener);
            mBinding.executePendingBindings();
        }
    }
}
