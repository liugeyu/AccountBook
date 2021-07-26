package com.geyu.callback;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public abstract class   OnPageChanageListenner extends RecyclerView.OnScrollListener {

    private final SnapHelper snapHelper;
    private int oldPosition = -1;//防止同一Position多次触发

    public OnPageChanageListenner(SnapHelper snapHelper) {
        this.snapHelper = snapHelper;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int position = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //获取当前选中的itemView
        View view = snapHelper.findSnapView(layoutManager);
        if (view != null) {
            //获取itemView的position
            position = layoutManager.getPosition(view);
        }
        if (newState == RecyclerView.SCROLL_STATE_IDLE && oldPosition != position) {
            oldPosition = position;
            onPageSelected(position);
        }
    }

    public abstract void onPageSelected(int position);
}
