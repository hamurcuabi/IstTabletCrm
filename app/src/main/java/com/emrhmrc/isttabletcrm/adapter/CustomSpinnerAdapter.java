package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.emrhmrc.isttabletcrm.models.Account.Account;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<Account> {
    public CustomSpinnerAdapter(Context context, List<Account> objects) {
        super(context, 0, objects);
    }
}
