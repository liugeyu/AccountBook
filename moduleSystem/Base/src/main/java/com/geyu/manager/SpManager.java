package com.geyu.manager;

import com.geyu.utils.SharedPrefsUtils;

public class SpManager {


    public static class AccountBooks {
        public static int getSelectId(){
            return SharedPrefsUtils.getIntegerPreference("AccountBookId",0);
        }

        public static void saveBookId(int bookId) {
            SharedPrefsUtils.setIntegerPreference("AccountBookId",bookId);
        }
    }


}
