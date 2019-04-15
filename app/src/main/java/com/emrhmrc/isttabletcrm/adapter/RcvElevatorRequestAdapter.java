package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.ElevatorFilterRequestAdapter;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointmentElevator;

public class RcvElevatorRequestAdapter extends GenericAdapter<ServiceAppointmentElevator,
        OnItemClickListener<ServiceAppointmentElevator>,
        RcvElevatorRequestViewHolder> implements Filterable {
    ElevatorFilterRequestAdapter filterAdapter;

    public RcvElevatorRequestAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filterAdapter = new ElevatorFilterRequestAdapter(this, getItemsFilter());
    }

    @Override
    public RcvElevatorRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        switch (viewType % 2) {
            case 0:
                return new RcvElevatorRequestViewHolder(inflate(R.layout.elevator_request_item,
                        parent));
            case 1:
                return new RcvElevatorRequestViewHolder(inflate(R.layout.elevator_request_item_2,
                        parent));
            default:
                return new RcvElevatorRequestViewHolder(inflate(R.layout.elevator_request_item,
                        parent));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return filterAdapter;
    }
}