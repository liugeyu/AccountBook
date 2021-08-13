package com.geyu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.geyu.base.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

public class TopBar extends FrameLayout {
    private TextView tvTitle;
    private Toolbar mToolbar;
    private String title;
    private int leftImgId = R.drawable.ic_black_back;
    View.OnClickListener backClickListener;
    public TopBar(@NonNull Context context) {
        this(context,null);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        title = a.getString(R.styleable.TopBar_text);
        leftImgId = a.getResourceId(R.styleable.TopBar_left_img,leftImgId);

        a.recycle();

        initView();
    }


    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.base_top_bar,this,true);
        tvTitle = findViewById(R.id.title);
        mToolbar = findViewById(R.id.tool_baar);
        tvTitle.setText(title);

//        findViewById(R.id.iv_back).setOnClickListener(this);
    }


    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitle(int res) {
        tvTitle.setText(res);
    }


    public void setBackClickListener(View.OnClickListener listener) {
        mToolbar.setNavigationIcon(leftImgId);
       mToolbar.setNavigationOnClickListener(listener);
    }

}
