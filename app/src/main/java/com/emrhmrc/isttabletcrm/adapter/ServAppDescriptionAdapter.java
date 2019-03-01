package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes;

public class ServAppDescriptionAdapter extends GenericAdapter<ServAppGetByIdNotes,
        OnItemClickListener<ServAppGetByIdNotes>,
        ServAppDescriptionViewHolder> {

    public ServAppDescriptionAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public ServAppDescriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new ServAppDescriptionViewHolder(inflate(R.layout.rcv_description_item, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}