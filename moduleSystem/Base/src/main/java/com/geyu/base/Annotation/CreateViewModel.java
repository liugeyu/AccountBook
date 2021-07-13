package com.geyu.base.Annotation;

import com.geyu.base.BaseViewModel;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateViewModel {

    Class<? extends BaseViewModel> value();

}
