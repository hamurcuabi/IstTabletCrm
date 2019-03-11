package com.emrhmrc.isttabletcrm.util;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class StringUtil {
    public final static String TEXT = "text/plain";
    public final static String PDF = "application/pdf";

    public static String returnNull() {
        return "";
    }

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
        else return "";
    }

    public static boolean isNull(String string) {
        if (string == null) return false;
        else return true;
    }

    public static String base64ToString(String string) {

        byte[] data = Base64.decode(string, Base64.DEFAULT);
        try {
            String text = new String(data, "UTF-8");
            return text;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "null";
        }

    }

}
