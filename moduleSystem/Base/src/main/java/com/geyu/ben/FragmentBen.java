package com.geyu.ben;

import com.geyu.base.BaseFragment;

import java.io.Serializable;
import java.util.List;

public class FragmentBen<T extends Serializable> {
    private Class<? extends BaseFragment> fragment;
    private T t;


    public FragmentBen(){
    }
    public FragmentBen(Class<? extends BaseFragment> clzz){
        fragment = clzz;
    }
    public FragmentBen(Class<? extends BaseFragment> clzz, T t){
        fragment = clzz;
        this.t = t;
    }
    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Class<? extends BaseFragment> getFragment() {
        return fragment;
    }

    public void setFragment(Class<? extends BaseFragment> fragment) {
        this.fragment = fragment;
    }
}
