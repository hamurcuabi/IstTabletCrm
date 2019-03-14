package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorChangingParts;

public class RcvChangingPartAdapter extends GenericAdapter<ElevatorChangingParts,
        OnItemClickListener<ElevatorChangingParts>,
        RcvChangingPartViewHolder> {


    public RcvChangingPartAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);

    }

    @Override
    public RcvChangingPartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        switch (viewType % 2) {
            case 0:
                return new RcvChangingPartViewHolder(inflate(R.layout.changgingpart_item, parent));
            case 1:
                return new RcvChangingPartViewHolder(inflate(R.layout.changgingpart_item_2,
                        parent));
            default:
                return new RcvChangingPartViewHolder(inflate(R.layout.changgingpart_item,
                        parent));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}