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
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RcvServAppDetailAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        this.context=context;
    }

    @Override
    public RcvServAppDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType % 2) {
            case 0:
                return new RcvServAppDetailViewHolder(inflate(R.layout.rcv_material_content_item,
                        parent),context);
            case 1:
                return new RcvServAppDetailViewHolder(inflate(R.layout.rcv_material_content_item_2, parent),context);
            default:
                return new RcvServAppDetailViewHolder(inflate(R.layout.rcv_material_content_item,
                        parent),context);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
