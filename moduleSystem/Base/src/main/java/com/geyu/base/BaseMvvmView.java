package com.geyu.base;

public interface BaseMvvmView<T> {
    void onItemClickListener(T t, int position);
}
