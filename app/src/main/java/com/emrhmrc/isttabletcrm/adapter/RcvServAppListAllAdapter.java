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


    public RcvServAppListAllAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public RcvSerAppListAllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new RcvSerAppListAllViewHolder(inflate(R.layout.isemri_bakim_item,
                        parent));
            case 2:
                return new RcvSerAppListAllViewHolder(inflate(R.layout.isemri_ariza_item,
                        parent));
            case 3:
                return new RcvSerAppListAllViewHolder(inflate(R.layout.isemri_modern_item,
                        parent));
            case 4:
                return new RcvSerAppListAllViewHolder(inflate(R.layout.isemri_yedek_item,
                        parent));
            case 5:
                return new RcvSerAppListAllViewHolder(inflate(R.layout.isemri_yillik_item,
                        parent));


            default:
                return new RcvSerAppListAllViewHolder(inflate(R.layout.isemri_bakim_item,
                        parent));
        }

    }

    @Override
    public int getItemViewType(int position) {
        final ServiceAppointments item=getItem(position);
        return item.getStatusCode().getValue();
    }
}