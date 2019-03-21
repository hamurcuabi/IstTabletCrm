package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownType;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppBreakdownTypes;

public class RcvBreakdownTypeAddAdapter extends GenericAdapter<ServAppGetByIdServAppBreakdownTypes,
        OnItemClickListener<ServAppGetByIdServAppBreakdownTypes>,
        BreakdownTypeAddHolder>  {



    public RcvBreakdownTypeAddAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);

    }

    @Override
    public BreakdownTypeAddHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new BreakdownTypeAddHolder(inflate(R.layout.breakdown_code_item_add, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}