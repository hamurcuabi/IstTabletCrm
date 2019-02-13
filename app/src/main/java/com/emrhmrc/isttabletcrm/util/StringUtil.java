package com.emrhmrc.isttabletcrm.util;

import android.text.TextUtils;

public class StringUtil {


    public static boolean validateStrings(String... string) {

        boolean validation = true;
        for (String current : string) {
            if (TextUtils.isEmpty(current)) {
                validation = false;
                break;
            }
        }

        return validation;
    }

    public static String convertIntToString(int value) {
        return String.valueOf(value);
    }

    public static String nullToString(String string) {
        if (validateStrings(string)) return string;
        else return "null";
    }

}