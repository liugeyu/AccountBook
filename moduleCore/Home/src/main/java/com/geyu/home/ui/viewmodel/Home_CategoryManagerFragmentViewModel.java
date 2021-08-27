package com.geyu.home.ui.viewmodel;

import com.geyu.database.ben.CategoryModel;
import com.geyu.home.ui.contract.Home_categoryManagerFragmentContract;
import com.geyu.manager.db.CategroyManager;
import com.geyu.utils.ErrHandler;
import com.geyu.utils.LLOG;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

public class Home_CategoryManagerFragmentViewModel extends Home_categoryManagerFragmentContract.ViewModel {
    private MutableLiveData<List<CategoryModel>> data = new MutableLiveData<>();
    private int mType;

    @Override
    public void initData(int type) {
        mType = type;
        Disposable d = CategroyManager.findByType(type)
                .doOnNext(categoryModels -> {
                    for (CategoryModel categoryModel : categoryModels) {
                        categoryModel.isSelect = true;
                    }
                })
                .subscribe(categoryModels -> {
                    data.setValue(categoryModels);
                },throwable -> showMessage(ErrHandler.getErrMsg(throwable)));
        addDisposable(d);
    }

    @Override
    public LiveData<List<CategoryModel>> getCategoryData() {
        return data;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LLOG.e("tttttt onCreate");

    }

    @Override
    public void onResume() {
        super.onResume();
        LLOG.e("tttttt onResume");
    }
}
