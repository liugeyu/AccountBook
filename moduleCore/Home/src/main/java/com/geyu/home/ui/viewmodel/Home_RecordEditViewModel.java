package com.geyu.home.ui.viewmodel;

import com.geyu.callback.event.RecordChanager;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.Record;
import com.geyu.db.AccountBookManager;
import com.geyu.db.AccountManager;
import com.geyu.db.CategroyManager;
import com.geyu.db.RecordDaoManager;
import com.geyu.home.ui.contract.Home_RecordEditContract;
import com.geyu.utils.AmountUtil;
import com.geyu.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

public class Home_RecordEditViewModel extends Home_RecordEditContract.ViewModel {

    MutableLiveData<List<CategoryModel>> categoryList = new MutableLiveData<>();

    @Override
    public void saveOrUpdateRecord(String amt, CategoryModel categoryModel, Record oldRecord) {
        if (oldRecord == null){
            oldRecord = new Record();
            oldRecord.setAmount(AmountUtil.amtToCent(amt));
            oldRecord.setAccountId(AccountManager.getAccountId());
            oldRecord.setTime(System.currentTimeMillis());
            oldRecord.setAccountBookId(AccountBookManager.getAccountBookId());
            oldRecord.setCategoryIcon(categoryModel.getIcon());
            oldRecord.setCategoryName(categoryModel.getName());
            oldRecord.setCategoryUniqueName(categoryModel.getUniqueName());
            oldRecord.setSyncId(StringUtils.getUuid());
        } else {
            oldRecord.setAmount(AmountUtil.amtToCent(amt));
            oldRecord.setCategoryIcon(categoryModel.getIcon());
            oldRecord.setCategoryName(categoryModel.getName());
            oldRecord.setCategoryUniqueName(categoryModel.getUniqueName());
        }

        RecordDaoManager.saveOrUpdate(oldRecord);
        EventBus.getDefault().post(new RecordChanager());
    }

    @Override
    public MutableLiveData<List<CategoryModel>> getCategoryData() {
        return categoryList;
    }


    void initCategory(){
        Disposable disposable = CategroyManager.findAll(CategoryModel.TYPE_EXPENSE)
                .subscribe(datas ->{
                    categoryList.setValue(datas);
                },throwable -> {

                });
        addDisposable(disposable);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initCategory();
    }
}
