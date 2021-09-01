package com.geyu.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geyu.base.BaseApplication;
import com.geyu.base.R;

import java.lang.ref.WeakReference;

public class ToastUtil {

    public static void showToast(String msg) {
        showShort(msg);
    }


    private static WeakReference<Toast> mToast;

    private static Toast initToast(CharSequence message, int duration) {

        if (mToast != null && mToast.get() != null) {
            mToast.get().cancel();
            mToast = null;
        }
        Toast toast = new Toast(BaseApplication.getContext());
        View view = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.toast_custom_tv, null);
        TextView tv = view.findViewById(R.id.tv_text);
        LinearLayout llContent = view.findViewById(R.id.ll_content);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) DisplayUtils.getDimension(R.dimen.dp_328),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llContent.setLayoutParams(lp);

        tv.setText(TextUtils.isEmpty(message) ? "" : message);
        toast.setDuration(duration);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
//        toast.show();

        mToast = new WeakReference<>(toast);
        return toast;
    }


    private static Toast initToast(Context ctx, CharSequence message, int duration) {
//        if (toast == null) {
//            toast = Toast.makeText(UIUtils.getContext(), message, duration);
//        } else {
//            toast.getView().setBackgroundResource(R.drawable.shape_text_golden_shadow);
//            toast.setText(message);
//            toast.setDuration(duration);
//        }

        if (mToast != null && mToast.get() != null) {
            mToast.get().cancel();
            mToast = null;
        }
        Toast toast = new Toast(ctx);
        View view = LayoutInflater.from(ctx).inflate(R.layout.toast_custom_tv, null);
        TextView tv = (TextView) view;
        tv.setText(TextUtils.isEmpty(message) ? "" : message);
        toast.setDuration(duration);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
//        toast.show();
        mToast = new WeakReference<>(toast);
        return toast;
    }


    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (TextUtils.isEmpty(message)) return;
        initToast(message, Toast.LENGTH_SHORT).show();

    }


    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(Context ctx, CharSequence message) {
        if (TextUtils.isEmpty(message)) return;
        initToast(ctx, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 短时间显示Toast
     *
     * @param strResId
     */
    public static void showShort(int strResId) {

//		Toast.makeText(context, strResId, Toast.LENGTH_SHORT).show();
        initToast(BaseApplication.getContext().getResources().getText(strResId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (TextUtils.isEmpty(message)) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            initToast(message, Toast.LENGTH_LONG).show();
        else Toast.makeText(BaseApplication.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param strResId
     */
    public static void showLong(int strResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            initToast(BaseApplication.getContext().getResources().getText(strResId), Toast.LENGTH_LONG).show();
        else Toast.makeText(BaseApplication.getContext(), strResId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        if (TextUtils.isEmpty(message)) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            initToast(message, duration).show();
        else Toast.makeText(BaseApplication.getContext(), message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param strResId
     * @param duration
     */
    public static void show(Context context, int strResId, int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            initToast(context.getResources().getText(strResId), duration).show();
        else Toast.makeText(context, strResId, duration).show();
    }
}
