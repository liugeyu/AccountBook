package com.geyu.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

public final class IntegerUtil {

    private IntegerUtil() {
    }

    /**
     *
     * @param 
     * @return int
     */
    public static int parseInt(String value) {
        if (TextUtils.isEmpty(value) || value.equalsIgnoreCase("null")) {
            return 0;
        }

        int result = 0;
        try {
            result = Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static long parseLong(String value) {
        if (TextUtils.isEmpty(value) || value.equalsIgnoreCase("null")) {
            return 0;
        }

        long result = 0;
        try {
            result = Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static double parseDouble(String value) {
        if (TextUtils.isEmpty(value) || value.equalsIgnoreCase("null")) {
            return 0;
        }

        double result = 0;
        try {
            result = Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
