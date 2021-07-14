package com.geyu.base;

import com.geyu.base.Annotation.CreateViewModel;

import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactoryImpl implements ViewModelFactory{
    private ViewModelFactoryImpl() {
    }
    private static volatile  ViewModelFactory instance;
    private static final Object OBJECT = new Object();
    public static ViewModelFactory getInstance(){
        if (instance == null) {
            synchronized (OBJECT) {
                if (instance == null) {
                    instance = new ViewModelFactoryImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public <T extends BaseViewModel> T createViewModel(BaseActivity activity) {
        CreateViewModel annotation = activity.getClass().getAnnotation(CreateViewModel.class);
        if (annotation == null) {
            throw new NullPointerException("在activity中没有写CreateViewModel注解ViewModel");
        }
        ViewModelProvider viewModelProvider = new ViewModelProvider(activity);
        T t = (T) viewModelProvider.get(annotation.value());
        return t;
    }
}
