package com.geyu.utils;

import android.text.TextUtils;

import com.geyu.base.R;
import com.geyu.data.CategoryIconHelper;

public class CategoryIconUtil extends CategoryIconHelper {

    public static int resId(String categoryIcName) {
        if (TextUtils.isEmpty(categoryIcName)) {
            return R.drawable.selector_category_other;
        }

        switch (categoryIcName) {
            case IC_NAME_OTHER:
                return R.drawable.selector_category_other;
            case IC_NAME_CAN_YIN:
                return R.drawable.selector_category_food;
            case IC_NAME_JIAO_TONG:
                return R.drawable.selector_category_jiao_tong;
            case IC_NAME_ZHU_FANG:
                return R.drawable.selector_category_house;
            case IC_NAME_GOU_WU:
                return R.drawable.selector_category_shopping;
            case IC_NAME_FU_SHI:
                return R.drawable.selector_category_fu_shi;
            case IC_NAME_RI_YONG_PIN:
                return R.drawable.selector_category_ri_yong;
            case IC_NAME_YU_LE:
                return R.drawable.selector_category_yu_le;
            case IC_NAME_SHI_CAI:
                return R.drawable.selector_category_shi_cai;
            case IC_NAME_LING_SHI:
                return R.drawable.selector_category_lin_shi;
            case IC_NAME_YI_LIAO:
                return R.drawable.selector_category_yi_liao;
            case IC_NAME_SHUI_DIAN_MEI:
                return R.drawable.selector_category_shui_dian;
            case IC_NAME_TONG_XUN:
                return R.drawable.selector_category_tong_xin;
            case IC_NAME_REN_QING:
                return R.drawable.selector_category_ren_qin;
            case IC_NAME_XUE_XI:
                return R.drawable.selector_category_xue_xi;
            case IC_SETTING:
                return R.drawable.selector_category_setting;
            default:
                return R.drawable.selector_category_other;
        }
    }
}
