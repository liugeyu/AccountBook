package com.geyu.utils;

import java.util.UUID;

public class StringUtils {
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
