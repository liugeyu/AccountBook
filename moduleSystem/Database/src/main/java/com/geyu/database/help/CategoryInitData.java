package com.geyu.database.help;

import com.geyu.database.ben.CategoryItem;
import com.geyu.database.ben.CategoryModel;
import com.geyu.database.data.CategoryConstant;
import com.geyu.database.data.CategoryIconHelper;

import java.util.ArrayList;
import java.util.List;

class CategoryInitData {
    /**
     * 支出初始化类型
     * @return
     */
    static List<CategoryItem> getExpenseInitData(){
      List<CategoryItem> datas = new ArrayList<>();
      datas.add(getExpenseItem(CategoryConstant.NAME_OTHER_EXPENSE,"其他", CategoryIconHelper.IC_NAME_OTHER));
      datas.add(getExpenseItem(CategoryConstant.NAME_CAN_YIN,"餐饮", CategoryIconHelper.IC_NAME_CAN_YIN));
      datas.add(getExpenseItem(CategoryConstant.NAME_JIAO_TONG,"交通", CategoryIconHelper.IC_NAME_JIAO_TONG));
      datas.add(getExpenseItem(CategoryConstant.NAME_GOU_WU,"购物", CategoryIconHelper.IC_NAME_GOU_WU));
      datas.add(getExpenseItem(CategoryConstant.NAME_FU_SHI,"服饰", CategoryIconHelper.IC_NAME_FU_SHI));
      datas.add(getExpenseItem(CategoryConstant.NAME_RI_YONG_PIN,"日用品", CategoryIconHelper.IC_NAME_RI_YONG_PIN));
      datas.add(getExpenseItem(CategoryConstant.NAME_YU_LE,"娱乐", CategoryIconHelper.IC_NAME_YU_LE));
      datas.add(getExpenseItem(CategoryConstant.NAME_SHI_CAI,"食材", CategoryIconHelper.IC_NAME_SHI_CAI));
      datas.add(getExpenseItem(CategoryConstant.NAME_LING_SHI,"零食", CategoryIconHelper.IC_NAME_LING_SHI));
      datas.add(getExpenseItem(CategoryConstant.NAME_YAN_JIU_CHA,"烟酒茶", CategoryIconHelper.IC_NAME_YAN_JIU_CHA));
      datas.add(getExpenseItem(CategoryConstant.NAME_XUE_XI,"学习", CategoryIconHelper.IC_NAME_XUE_XI));
      datas.add(getExpenseItem(CategoryConstant.NAME_YI_LIAO,"医疗", CategoryIconHelper.IC_NAME_YI_LIAO));
      datas.add(getExpenseItem(CategoryConstant.NAME_ZHU_FANG,"住房", CategoryIconHelper.IC_NAME_ZHU_FANG));
      datas.add(getExpenseItem(CategoryConstant.NAME_SHUI_DIAN_MEI,"水电煤", CategoryIconHelper.IC_NAME_SHUI_DIAN_MEI));
      datas.add(getExpenseItem(CategoryConstant.NAME_TONG_XUN,"通讯", CategoryIconHelper.IC_NAME_TONG_XUN));
      datas.add(getExpenseItem(CategoryConstant.NAME_REN_QING,"人情来往", CategoryIconHelper.IC_NAME_REN_QING));
      return datas;
    }


    private static CategoryItem getExpenseItem(String uniqueName, String name, String icon){
        CategoryItem categoryItem = new CategoryItem(uniqueName,name,icon);
        categoryItem.type = CategoryModel.TYPE_EXPENSE;
        return categoryItem;
    }
}
