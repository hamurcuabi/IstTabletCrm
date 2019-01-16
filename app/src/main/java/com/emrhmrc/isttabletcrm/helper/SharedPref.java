package com.emrhmrc.isttabletcrm.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedPref {
    // Shared preferences file name
    private static final String PREF_NAME = "PrefManager";
    private static final String USER_MAIL = "UserMail";
    private static final String USER_PASS = "UserPass";
    private static final String REMEMBER_ME = "RememberMeCb";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    public SharedPref(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public String getUserMail() {
        return pref.getString(USER_MAIL, "");
    }

    public void setUserMail(String mail) {
        editor.putString(USER_MAIL, mail);
        editor.commit();
    }

    public String getUserPass() {
        return pref.getString(USER_PASS, "");
    }

    public void setUserPass(String pass) {
        editor.putString(USER_PASS, pass);
        editor.commit();
    }
    public boolean getRememberMe() {
        return pref.getBoolean(REMEMBER_ME, true);
    }

    public void setRememberMe(boolean checked) {
        editor.putBoolean(REMEMBER_ME, checked);
        editor.commit();
    }
}
