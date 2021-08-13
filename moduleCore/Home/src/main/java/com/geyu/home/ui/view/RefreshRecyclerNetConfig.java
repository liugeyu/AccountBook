package com.geyu.home.ui.view;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @Author zhangshuqi
 * @CreateTime 2018/3/20
 * @Describe
 */

public abstract class RefreshRecyclerNetConfig<T> {
    public abstract Observable<List<T>> getNetObservable(Map<String, Object> map, int action);
}
