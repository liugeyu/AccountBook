package com.geyu.base;

import com.geyu.base.Annotation.CreateViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public interface ViewModelFactory {
    <T extends BaseViewModel> T createViewModel(BaseActivity activity);
    <T extends BaseViewModel> T createViewModel(BaseFragment fragment);

}
