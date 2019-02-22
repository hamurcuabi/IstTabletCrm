package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.models.Account.Account;

import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {
    private List<Account> mList;
    private Context mContext;
    private LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, List<Account> objects) {
        this.mContext = context;
        this.mList = objects;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Account getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spinner_item, null);
        TextView names = view.findViewById(R.id.txt_spinner);
        names.setText(mList.get(i).getName());
        return view;
    }


}
