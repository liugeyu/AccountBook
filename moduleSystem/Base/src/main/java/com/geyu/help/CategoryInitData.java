package com.geyu.help;

import com.geyu.database.ben.CategoryItem;
import com.geyu.database.ben.CategoryModel;
import com.geyu.data.CategoryConstant;
import com.geyu.data.CategoryIconHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryInitData {
    /**
     * 支出初始化类型
     * @return
     */
    public static List<CategoryItem> getExpenseInitData(){
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

    /**
     * 获取收入初始化类型
     */
    public static List<CategoryItem> getIncomeInitData (){
        List<CategoryItem> datas = new ArrayList<>();

        datas.add(getIncomeItem(CategoryConstant.NAME_OTHER_IN_COME,"其他",CategoryIconHelper.IC_NAME_INCOME_OTHER));
        datas.add(getIncomeItem(CategoryConstant.NAME_XIN_ZI,"薪资",CategoryIconHelper.IC_NAME_XIN_ZI));
        datas.add(getIncomeItem(CategoryConstant.NAME_JIANG_JIN,"奖金",CategoryIconHelper.IC_NAME_JIANG_JIN));
        datas.add(getIncomeItem(CategoryConstant.NAME_JIE_RU,"借入",CategoryIconHelper.IC_NAME_JIE_RU));
        datas.add(getIncomeItem(CategoryConstant.NAME_SHOU_ZHAI,"收债",CategoryIconHelper.IC_NAME_SHOU_ZHAI));
        datas.add(getIncomeItem(CategoryConstant.NAME_LI_XIN_SHOU_RU,"利息",CategoryIconHelper.IC_NAME_LI_XIN_SHOU_RU));
        datas.add(getIncomeItem(CategoryConstant.NAME_TOU_ZI_HUI_SHOU,"投资回收",CategoryIconHelper.IC_NAME_TOU_ZI_HUI_SHOU));
        datas.add(getIncomeItem(CategoryConstant.NAME_YI_WAI_SUO_DE,"意外所得",CategoryIconHelper.IC_NAME_YI_WAI_SUO_DE));
        datas.add(getIncomeItem(CategoryConstant.NAME_TOU_ZI_SHOU_YI,"投资收益",CategoryIconHelper.IC_NAME_TOU_ZI_SHOU_YI));

        return datas;
    }

    private static CategoryItem getIncomeItem(String uniqueName, String name, String icon){
        CategoryItem categoryItem = new CategoryItem(uniqueName,name,icon);
        categoryItem.type = CategoryModel.TYPE_INCOME;
        return categoryItem;
    }
}
