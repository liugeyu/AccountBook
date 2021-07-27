package com.geyu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.geyu.base.R;
import com.geyu.base.databinding.NumericKeypadBinding;
import com.geyu.callback.NumericKeypadConfirm;
import com.geyu.utils.AmountUtil;
import com.geyu.utils.DataBindingUtils;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class NumericKeypad extends FrameLayout {

    private TextView editText;
    private StringBuffer sb = new StringBuffer();
    private NumericKeypadBinding mBinding;

    private NumericKeypadConfirm confirm;

    public NumericKeypad(@NonNull Context context) {
        this(context,null);
    }

    public NumericKeypad(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumericKeypad(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.numeric_keypad,this,true);
        mBinding.setVm(this);
    }
    public void setEditText(TextView editText) {
        this.editText = editText;
    }

    public void setListener(NumericKeypadConfirm confirm) {
        this.confirm = confirm;
    }

    public void onNumberClick(String num){

        if (sb.indexOf("0") == 0 && sb.length() == 1) {

        } else {
            if (AmountUtil.validAmount(sb.toString() + num)) {
                sb.append(num);
            }
        }
        setText();
    }
    public void onDeleteClick(){
        if (sb.length() > 0 ){
            sb.delete(sb.length()-1,sb.length());
        }
        setText();
    }
    public void onClearClick(){
        if (sb.length() > 0){
            sb.delete(0,sb.length());
        }
        setText();
    }
    public void onDotClick(){
        if (sb.indexOf(".") == -1) {
            sb.append(".");
        }
        setText();
    }
    public void onEnterClick(){
        confirm.onConfirm();
    }

    private void setText(){
        if (editText != null) {
            if (sb.length() > 0) {
                DataBindingUtils.setAmount(editText,AmountUtil.amtToCent(sb.toString()));
            } else {
                DataBindingUtils.setAmount(editText,AmountUtil.amtToCent("0.00"));
            }
        }
    }
}
