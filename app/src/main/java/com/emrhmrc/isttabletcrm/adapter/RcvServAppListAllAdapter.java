package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointments;

public class RcvServAppListAllAdapter extends GenericAdapter<ServiceAppointments,
        OnItemClickListener<ServiceAppointments>,
        RcvSerAppListAllViewHolder> {


    public RcvServAppListAllAdapter(Context context,OnItemClickListener listener) {
        super(context,listener);
    }

    @Override
    public RcvSerAppListAllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RcvSerAppListAllViewHolder(inflate(R.layout.isemri_yedek_item, parent));
    }
}
