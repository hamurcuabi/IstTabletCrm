package com.emrhmrc.isttabletcrm.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Methodes {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1010;
    public static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public static final String textToMd5(final String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean validateInput(String input) {
        return TextUtils.isEmpty(input) ? false : true;
    }

    public static boolean checkAndRequestPermissions(Activity activity) {
        int permissionACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCAMERA = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);
        int permissionACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionACCESS_READ = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionACCESS_WRITE = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (permissionACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissionACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (permissionACCESS_READ != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionACCESS_WRITE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new
                    String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    public static String changeDateFormatToText(String date) {

        if (validateInput(date)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date sourceDate = null;
            try {
                sourceDate = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return "null";

            }

            SimpleDateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");
            return targetFormat.format(sourceDate);
        } else return "null";


    }

    public static String changeDateFormatToTextWithClock(String date) {

        if (validateInput(date)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date sourceDate = null;
            try {
                sourceDate = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return "null";

            }

            SimpleDateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy / HH:mm");
            return targetFormat.format(sourceDate);
        } else return "null";


    }

    public static int compareDateFromText(String first, String second) {

        if (validateInput(first) && validateInput(second)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date sourceDateFirst = null;
            Date sourceDateSecond = null;
            try {
                sourceDateFirst = dateFormat.parse(first);
                sourceDateSecond = dateFormat.parse(second);
                return sourceDateFirst.compareTo(sourceDateSecond);
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        } else return 0;


    }


    public static String changeDateFormatToClockText(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat targetFormat = new SimpleDateFormat("HH:mm");
        return targetFormat.format(sourceDate);


    }


    public static Calendar changeDateFormatToDate(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date sourceDate = null;
        Calendar calendar = Calendar.getInstance();
        try {
            sourceDate = dateFormat.parse(date);
            calendar.setTime(sourceDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return calendar;


    }

    public static String getCurrenttime() {

        Date sourceDate = Calendar.getInstance().getTime();
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");
        return targetFormat.format(sourceDate);
    }

    public static String getCurrentClock() {

        Date sourceDate = Calendar.getInstance().getTime();
        SimpleDateFormat targetFormat = new SimpleDateFormat("HH:mm");
        return targetFormat.format(sourceDate);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
