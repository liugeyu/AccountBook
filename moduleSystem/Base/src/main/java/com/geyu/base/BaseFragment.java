package com.geyu.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public  class BaseFragment<D extends Serializable> extends Fragment {

    private static final String DATA_KEY = "DATA_KEY";
    protected FragmentActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (FragmentActivity) context;
    }

    public static BaseFragment newInstance(Class<? extends BaseFragment> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BaseFragment<D> bindData(D d) {
        Bundle bundle =  new Bundle();
        bundle.putSerializable(DATA_KEY,d);
        setArguments(bundle);
        return this;
    }


    private void getData(Bundle bundle) {
        if (bundle != null){
            Serializable serializable = bundle.getSerializable(DATA_KEY);
            if (serializable != null) {
                getData((D) serializable);
            }
        }
    }

    protected void getData(D d){
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData(getArguments());
        initView();
        initData();
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void onBack(){
        if (mActivity!= null && mActivity instanceof BaseActivity) {
            ((BaseActivity) mActivity).onBack();
        }
    }
}
