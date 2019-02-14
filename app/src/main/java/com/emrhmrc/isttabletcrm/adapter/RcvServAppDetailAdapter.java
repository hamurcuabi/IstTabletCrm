package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;

public class RcvServAppDetailAdapter extends GenericAdapter<ServAppGetByIdServAppDetails,
        OnItemClickListener<ServAppGetByIdServAppDetails>,
        RcvServAppDetailViewHolder>{
    public RcvServAppDetailAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public RcvServAppDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RcvServAppDetailViewHolder(inflate(R.layout.rcv_material_content_item, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



}
