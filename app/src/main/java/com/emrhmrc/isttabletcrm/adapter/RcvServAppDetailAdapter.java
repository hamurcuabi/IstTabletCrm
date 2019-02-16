package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;

public class RcvServAppDetailAdapter extends GenericAdapter<ServAppGetByIdServAppDetails,
        OnItemClickListener<ServAppGetByIdServAppDetails>,
        RcvServAppDetailViewHolder> {
    public RcvServAppDetailAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public RcvServAppDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType % 2) {
            case 0:
                return new RcvServAppDetailViewHolder(inflate(R.layout.rcv_material_content_item, parent));
            case 1:
                return new RcvServAppDetailViewHolder(inflate(R.layout.rcv_material_content_item_2, parent));
            default:
                return new RcvServAppDetailViewHolder(inflate(R.layout.rcv_material_content_item, parent));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
