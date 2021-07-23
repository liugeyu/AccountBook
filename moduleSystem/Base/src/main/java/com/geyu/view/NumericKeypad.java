package com.geyu.view;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.geyu.base.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NumericKeypad extends KeyboardView {
    public NumericKeypad(@NonNull Context context) {
        this(context,null);
    }

    public NumericKeypad(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumericKeypad(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
