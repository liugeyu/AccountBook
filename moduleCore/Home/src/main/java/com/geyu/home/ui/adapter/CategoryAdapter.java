package com.geyu.home.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.geyu.adapter.BaseViewHolder;
import com.geyu.database.ben.CategoryModel;
import com.geyu.home.R;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

public class CategoryAdapter extends HomeBaseAdapter<CategoryModel> {

    public CategoryAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new HomeBaseViewHolder(DataBindingUtil.inflate(layoutInflater,R.layout.home_item_category,parent,false));
    }


    public CategoryModel getSelect(){
        for (CategoryModel data : this.datas) {
            if (data.isSelect) {
                return data;
            }
        }
        return null;
    }
}
