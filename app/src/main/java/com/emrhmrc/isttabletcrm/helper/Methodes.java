package com.emrhmrc.isttabletcrm.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        int permissionINTERNET = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.INTERNET);
        int permissionACCESS_NETWORK_STATE = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_NETWORK_STATE);
        int permissionACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionACCESS_VIBRATE = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.VIBRATE);
        int permissionCAMERA = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA);
        int permissionACCESS_LOCATION_EXTRA_COMMANDS = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
        int permissionACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionWRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionACCESS_WIFI_STATE = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_WIFI_STATE);
        int permissionCHANGE_WIFI_STATE = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CHANGE_WIFI_STATE);
        int permissionREAD_PHONE_STATE = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_PHONE_STATE);
        int permissionRECEIVE_BOOT_COMPLETED = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.RECEIVE_BOOT_COMPLETED);
        int permissionBLUETOOTH_ADMIN = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.BLUETOOTH_ADMIN);
        int permissionBLUETOOTH = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.BLUETOOTH);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissionACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new
                    String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
