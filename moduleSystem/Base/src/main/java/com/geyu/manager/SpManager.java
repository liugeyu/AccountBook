package com.geyu.manager;

import com.geyu.utils.SharedPrefsUtils;

public class SpManager {


    public static class AccountBooks {
        public static long getSelectId(){
            return SharedPrefsUtils.getLongPreference("AccountBookId",0);
        }

        public static void saveBookId(long bookId) {
            SharedPrefsUtils.setLongPreference("AccountBookId",bookId);
        }
    }


}
