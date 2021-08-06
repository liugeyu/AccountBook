package com.geyu.utils;

import com.geyu.base.BaseApplication;

import java.io.File;

public class FileUtil {

    private FileUtil(){}


    public static File getBackupFile(){
        File file = new File(getCache(),"backup/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;

    }


    private static File getCache(){
        return BaseApplication.getContext().getExternalCacheDir();
    }
}
