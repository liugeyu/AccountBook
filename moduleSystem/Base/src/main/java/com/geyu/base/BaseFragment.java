package com.geyu.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public  class BaseFragment<D extends Serializable> extends Fragment {

    private static final String DATA_KEY = "DATA_KEY";

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
    }
}
