package com.geyu.utils;

import android.text.TextUtils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AmountUtil {

    private AmountUtil() {}


    public static long amtToCent(String dollar) {
        String str = toCent(dollar);
        return Long.parseLong(str);
    }
    /**
     * 以元为单位的金额表示转换成分为单位的金额表示，如1.00转换成000000000100格式表示。
     * @param dollar 以元为单位的金额表示，如123.00
     * @return 以分为单位的金额表示，如000000012300
     */
    public static String toCent(String dollar) {
        if (TextUtils.isEmpty(dollar)) {
            return String.format(Locale.US, "%012d", 0);
        }

        String cent = ""; // .5 1.
        int index = dollar.indexOf(".");
        if (index >= 0) {
            int gap = dollar.length() - index - 1;
            if (gap == 0) {
                cent = dollar + "00";
                cent = cent.replace(".","");
            } else if (gap == 1) {
                cent = dollar.replace(".", "") + "0";
            } else if (gap == 2) {
                cent = dollar.replace(".", "");
            } else {
                cent = dollar.substring(0, index + 3).replace(".", "");
            }
        } else {
            cent = dollar + "00";
        }

        long cents = 0;
        try {
            cents = IntegerUtil.parseLong(cent);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return String.format(Locale.US, "%012d", cents);
    }

    /**
     * 以分为单位的金额表示，转换成以元为单位的金额表示，如：12300转换成123.00
     * @param cent 以分为单位的金额表示，如12300，000000012300
     * @return 以元为单位的金额表示，如123.00
     */
    public static String toDollar(String cent) {
        if (TextUtils.isEmpty(cent)) {
            return "0.00";
        }

        int index = cent.indexOf(".");
        if (index >= 0) {
            return cent.substring(0, index);
        }

        long cents = 0;
        try {
            cents = IntegerUtil.parseLong(cent);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return String.format(Locale.US, "%.02f", cents / 100.0);
    }

    /**
     * 以分为单位的金额表示，转换成以元为单位的金额表示，如：12300转换成123.00
     * @param cent cent 以分为单位的金额表示，如12300
     * @return 以元为单位的金额表示，如123.00
     */
    public static String toDollar(int cent) {
        return toDollar(Integer.toString(cent));
    }

    /**
     * 以分为单位的金额表示，转换成以元为单位的金额表示，如：12300转换成123.00
     * @param cent cent 以分为单位的金额表示，如12300
     * @return 以元为单位的金额表示，如123.00
     */
    public static String toDollar(long cent) {
        return toDollar(String.valueOf(cent));
    }

    /**
     * 判断以元为单位的金额是否有效
     * @param dollar 金额
     * @return 是否有效金额
     */
    public static boolean isValid(String dollar) {
        if (TextUtils.isEmpty(dollar)) {
            return false;
        }
        long cent = IntegerUtil.parseLong(toCent(dollar));
        return cent > 0;
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

    /**
     * 是否为最多两位小数的金额
     * @param amount
     * @return
     */
    public static boolean validAmount(String amount) {
        if (TextUtils.isEmpty(amount)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^([\\d]+|[\\d]+\\.[\\d.]{1,2})$");
        Matcher matcher = pattern.matcher(amount);
        return matcher.matches();
    }
}
