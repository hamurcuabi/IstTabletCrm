package com.emrhmrc.isttabletcrm.helper;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.emrhmrc.isttabletcrm.R;

public class ViewDialog {

    Activity activity;
    Dialog dialog;
    public ViewDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {

        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cutom_loading);
        dialog.show();
    }

    public void hideDialog(){
        dialog.dismiss();
    }

}