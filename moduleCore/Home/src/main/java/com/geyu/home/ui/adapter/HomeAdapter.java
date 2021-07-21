package com.geyu.home.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.geyu.adapter.BaseAdapter;
import com.geyu.adapter.BaseViewHolder;
import com.geyu.database.ben.Record;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter  extends BaseAdapter<Record> {
    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

}
