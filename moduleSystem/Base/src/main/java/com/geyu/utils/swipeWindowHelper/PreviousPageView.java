package com.geyu.utils.swipeWindowHelper;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * @Author zhangshuqi
 * @CreateTime 2018/3/27
 * @Describe
 */

public class PreviousPageView extends View {
    private View mView;

    public PreviousPageView(Context context) {
        super(context);
    }

    public void cacheView(View view) {
        mView = view;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mView != null) {
            mView.draw(canvas);
            mView = null;
        }
    }
}
