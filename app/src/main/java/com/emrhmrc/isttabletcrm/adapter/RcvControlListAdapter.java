package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppModernizationChecklists;

public class RcvControlListAdapter extends GenericAdapter<ServAppGetByIdServAppModernizationChecklists,
        OnItemClickListener<ServAppGetByIdServAppModernizationChecklists>,
        RcvControlListViewHolder> {


    public RcvControlListAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);

    }

    @Override
    public RcvControlListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new RcvControlListViewHolder(inflate(R.layout.control_list_item, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}