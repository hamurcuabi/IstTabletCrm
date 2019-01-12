package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;

public class ServAppDetailAdapter extends GenericAdapter<ServAppGetByIdServAppDetails,
        OnItemClickListener<ServAppGetByIdServAppDetails>,
        ServAppDetailViewHolder> {
    public ServAppDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public ServAppDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServAppDetailViewHolder(inflate(R.layout.rcv_malzeme_icerik_item, parent));
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
