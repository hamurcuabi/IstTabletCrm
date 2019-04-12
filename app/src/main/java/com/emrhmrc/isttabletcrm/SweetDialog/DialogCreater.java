package com.emrhmrc.isttabletcrm.SweetDialog;

import android.app.Activity;

public class DialogCreater {


    public static void basicDialog(Activity activity, String title) {
        new SweetAlertDialog(activity)
                .setTitleText(title)
                .show();
    }

    public static void basicDialog(Activity activity, String title, String content) {
        new SweetAlertDialog(activity)
                .setTitleText(title)
                .setContentText(content)
                .show();
    }

    public static void errorDialog(Activity activity, String content) {
        new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("")
                .setContentText(content)
                .show();
    }

    public static void succesDialog(Activity activity, String content) {
        new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("")
                .setContentText(content)
                .show();
    }
}
