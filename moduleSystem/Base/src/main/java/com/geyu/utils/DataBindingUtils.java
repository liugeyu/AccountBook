package com.geyu.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geyu.database.data.CategoryIconHelper;

import androidx.databinding.BindingAdapter;

public class DataBindingUtils {



    @BindingAdapter("bindText")
    public static void bindText(TextView tv, int value){
        bindText(tv,""+value);
    }
    @BindingAdapter("bindText")
    public static void bindText(TextView tv, long value){
        bindText(tv,""+value);
    }
    @BindingAdapter("bindText")
    public static void bindText(TextView tv, String value) {
        tv.setText(value);
    }



    @BindingAdapter("categoryIcon")
    public static void categoryIcon(ImageView iv, String iconName){
        if (TextUtils.isEmpty(iconName)) {
            return;
        }
        int resId = CategoryIconUtil.resId(iconName);
        iv.setImageResource(resId);
    }


    @BindingAdapter(value = "selected")
    public static void selected(View view,boolean isSelected){
        view.setSelected(isSelected);
    }

    @BindingAdapter("setAmount")
    public static void setAmount(TextView tv, long amt) {
        tv.setText(String.format("¥:%s", AmountUtil.toDollar(amt)));
    }
    @BindingAdapter("setTime")
    public static void setTime(TextView tv, long time) {
        tv.setText(TimeUtil.getRecordDisplayDate(time));
    }
}
