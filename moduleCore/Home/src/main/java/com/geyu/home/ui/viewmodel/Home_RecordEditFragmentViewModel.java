package com.geyu.home.ui.viewmodel;

import com.geyu.callback.event.RecordChanager;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.ben.Record;
import com.geyu.data.CategoryIconHelper;
import com.geyu.manager.db.AccountBookManager;
import com.geyu.manager.db.AccountManager;
import com.geyu.manager.db.CategroyManager;
import com.geyu.manager.db.RecordDaoManager;
import com.geyu.home.ui.contract.Home_RecordEditFragmentContract;
import com.geyu.utils.AmountUtil;
import com.geyu.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

public class Home_RecordEditFragmentViewModel extends Home_RecordEditFragmentContract.ViewModel {
    MutableLiveData<List<CategoryModel>> categoryList = new MutableLiveData<>();

    private CategoryModel setting = new CategoryModel();
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
            oldRecord.setType(categoryModel.getType());
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


   public void initCategory(int type){
        Disposable disposable = CategroyManager.findByType(type)
                .subscribe(datas ->{
                    setting.setType(type);
                    datas.add(setting);
                    categoryList.setValue(datas);
                },throwable -> {

                });
        addDisposable(disposable);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        setting.setIcon(CategoryIconHelper.IC_SETTING);
        setting.setName("管理分类");
        setting.setAccountBookId(AccountManager.getAccountId());
    }
}
